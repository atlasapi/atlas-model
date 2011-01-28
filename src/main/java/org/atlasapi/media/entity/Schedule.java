package org.atlasapi.media.entity;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.Interval;

import com.google.common.base.Function;
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

	public static Schedule fromItems(String channel, Interval interval, Iterable<? extends Item> items) {
		HashMultimap<String, ItemAndBroadcast> map = HashMultimap.create();
		for (Item item : filter(items, channel, interval)) {
			for (Version version : item.getVersions()) {
				for (Broadcast broadcast : version.getBroadcasts()) {
					map.put(broadcast.getBroadcastOn(), new ItemAndBroadcast(item, broadcast));
				}
			}
		}
		Map<String, List<Item>> mmap = Maps.newHashMap();
		for (Entry<String, Collection<ItemAndBroadcast>> entry : map.asMap().entrySet()) {
			ImmutableList<ItemAndBroadcast> sorted = Ordering.natural().immutableSortedCopy(entry.getValue());
			mmap.put(entry.getKey(), Lists.transform(sorted, TO_ITEM));
		}
		return new Schedule(mmap);
	}
	
	private static final class ItemAndBroadcast implements Comparable<ItemAndBroadcast> {
		
		private final Item item;
		private final Broadcast broadcast;
		
		public ItemAndBroadcast(Item item, Broadcast broadcast) {
			this.item = item;
			this.broadcast = broadcast;
		}

		@Override
		public int compareTo(ItemAndBroadcast other) {
			return broadcast.getTransmissionTime().compareTo(other.broadcast.getTransmissionTime());
		}
		
	}
	
	private static Function<ItemAndBroadcast, Item> TO_ITEM =  new Function<ItemAndBroadcast, Item>() {
		@Override
		public Item apply(ItemAndBroadcast input) {
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

	private static List<Item> filter(Iterable<? extends Item> items, final String service, final Interval localInterval) {
		
		final Predicate<Broadcast> validBroadcast = new Predicate<Broadcast>() {
			@Override
			public boolean apply(Broadcast broadcast) {
				return service.equals(broadcast.getBroadcastOn())
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
