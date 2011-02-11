package org.atlasapi.media.entity;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.joda.time.Interval;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public final class Schedule {

	private final Map<String, List<Item>> channelMap;

	public List<Item> getItemsFromOnlyChannel() {
		if (channelMap.size() == 0) {
			throw new IllegalArgumentException("No channels in schedule");
		}
		if (channelMap.size() != 1) {
			throw new IllegalArgumentException("Multiple channels found");
		}
		return Iterables.getOnlyElement(channelMap.values());
	}

	public static Schedule fromItems(Iterable<String> channels, Interval interval, Iterable<? extends Item> items) {
		HashMultimap<String, ScheduleEntry> map = HashMultimap.create();
		for (Item item : filter(items, ImmutableSet.copyOf(channels), interval)) {
			for (Version version : item.nativeVersions()) {
				for (Broadcast broadcast : version.getBroadcasts()) {
					map.put(broadcast.getBroadcastOn(), new ScheduleEntry(item, broadcast));
				}
			}
		}
		Map<String, List<Item>> mmap = Maps.newHashMap();
		for (String channel : channels) {
			ImmutableList<ScheduleEntry> sorted = Ordering.natural().immutableSortedCopy(map.get(channel));
			mmap.put(channel, Lists.transform(sorted, TO_ITEM));
		}
		return new Schedule(mmap);
	}
	
	public List<ScheduleChannel> toScheduleChannels() {
	    ImmutableList.Builder<ScheduleChannel> builder = ImmutableList.builder();
	    
	    for (Entry<String, List<Item>> entry: channelMap.entrySet()) {
	        builder.add(new ScheduleChannel(entry.getKey(), entry.getValue()));
	    }
	    
	    return builder.build();
	}
	
	public static class ScheduleChannel {
	    private final String channel;
        private final List<Item> items;

        public ScheduleChannel(String channel, List<Item> items) {
            Preconditions.checkNotNull(channel);
            Preconditions.checkNotNull(items);
            
            this.channel = channel;
            this.items = items;
        }
        
        public String channel() {
            return channel;
        }
        
        public List<Item> items() {
            return items;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ScheduleChannel) {
                ScheduleChannel scheduleChannel = (ScheduleChannel) obj;
                return channel.equals(scheduleChannel.channel) && items.equals(scheduleChannel.items);
            }
            return false;
        }
        
        @Override
        public int hashCode() {
            return channel.hashCode();
        }
        
        @Override
        public String toString() {
            return Objects.toStringHelper(ScheduleChannel.class).addValue(channel).addValue(items).toString();
        }
	}
	
	private static final class ScheduleEntry implements Comparable<ScheduleEntry> {
		
		private final Item item;
		private final Broadcast broadcast;
		
		public ScheduleEntry(Item item, Broadcast broadcast) {
			this.item = item;
			this.broadcast = broadcast;
		}

		@Override
		public int compareTo(ScheduleEntry other) {
			return broadcast.getTransmissionTime().compareTo(other.broadcast.getTransmissionTime());
		}
		
	}
	
	private static Function<ScheduleEntry, Item> TO_ITEM =  new Function<ScheduleEntry, Item>() {
		@Override
		public Item apply(ScheduleEntry input) {
			return input.item;
		}
	};
	
	private Schedule(Map<String, List<Item>> channelMap) {
		this.channelMap = channelMap;
	}
	
	@Override
	public int hashCode() {
		return channelMap.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Schedule) {
			return channelMap.equals(((Schedule) obj).channelMap);
		}
		return false;
	}
	
	private static final Predicate<Version> HAS_BROADCASTS = new Predicate<Version>() {
		@Override
		public boolean apply(Version version) {
			return !version.getBroadcasts().isEmpty();
		}
	};

	private static List<Item> filter(Iterable<? extends Item> items, final Set<String> services, final Interval localInterval) {
		
		final Predicate<Broadcast> validBroadcast = new Predicate<Broadcast>() {
			@Override
			public boolean apply(Broadcast broadcast) {
				return services.contains(broadcast.getBroadcastOn())
					&& localInterval.contains(broadcast.getTransmissionTime());
			}
		};
		
		final Function<Version, Version> broadcastFilter = new Function<Version, Version>() {
			@Override
			public Version apply(Version version) {
				version.setBroadcasts(ImmutableSet.copyOf(Iterables.filter(version.getBroadcasts(), validBroadcast)));
				return version;
			}
		};
		
		return ImmutableList.copyOf(Iterables.transform(items, new Function<Item, Item>() {
			@Override
			public Item apply(Item item) {
				item.setVersions(ImmutableSet.copyOf(Iterables.filter(Iterables.transform(item.getVersions(), broadcastFilter), HAS_BROADCASTS)));
				return item;
			}
		}));
	}
}
