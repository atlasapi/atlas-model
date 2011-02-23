package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.Interval;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public final class Schedule {

    private final Interval interval;

	public static Schedule fromItems(Interval interval, Iterable<? extends Item> items) {
        ImmutableSet.Builder<String> channels = ImmutableSet.builder();
        for (Item item : items) {
            for (Version version : item.getVersions()) {
                for (Broadcast broadcast : version.getBroadcasts()) {
                    channels.add(broadcast.getBroadcastOn());
                }
            }
        }
        return fromItems(channels.build(), interval, items);
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
        Map<String, ScheduleChannel> mmap = Maps.newHashMap();
        for (String channel : channels) {
            ImmutableList<ScheduleEntry> sorted = Ordering.natural().immutableSortedCopy(map.get(channel));
            mmap.put(channel, new ScheduleChannel(channel, sorted));
        }
        return new Schedule(mmap, interval);
    }

    private final Map<String, ScheduleChannel> channelMap;

    private Schedule(Map<String, ScheduleChannel> channelMap, Interval interval) {
        this.channelMap = channelMap;
        this.interval = interval;
    }
    
    public Interval interval() {
        return interval;
    }
    
    @Deprecated
    public List<Item> getItemsFromOnlyChannel() {
        if (channelMap.size() == 0) {
            throw new IllegalArgumentException("No channels in schedule");
        }
        if (channelMap.size() != 1) {
            throw new IllegalArgumentException("Multiple channels found");
        }
        return ImmutableList.copyOf(Iterables.transform(Iterables.getOnlyElement(channelMap.values()).entries(), ScheduleEntry.TO_ITEM));
    }
    
    public List<ScheduleEntry> getEntriesForOnlyChannel() {
        if (channelMap.size() == 0) {
            throw new IllegalArgumentException("No channels in schedule");
        }
        if (channelMap.size() != 1) {
            throw new IllegalArgumentException("Multiple channels found");
        }
        return Iterables.getOnlyElement(channelMap.values()).entries();
    }

    public List<ScheduleChannel> toScheduleChannels() {
        return Lists.newArrayList(channelMap.values());
    }
    
    public List<ScheduleEntry> entriesForChannel(String channel) {
        return channelMap.get(channel).entries();
    }

    public static class ScheduleChannel {
        private final String channel;
        private final List<ScheduleEntry> entries;

        public ScheduleChannel(String channel, Iterable<ScheduleEntry> entries) {
            checkNotNull(channel);
            checkNotNull(entries);
            this.channel = channel;
            this.entries = ImmutableList.copyOf(entries);
        }

        public String channel() {
            return channel;
        }

        public List<Item> items() {
            return ImmutableList.copyOf(Iterables.transform(entries, ScheduleEntry.TO_ITEM));
        }

        public List<ScheduleEntry> entries() {
            return entries;
        }
        
        public void removeItem(Item item) {
//            items.remove(item);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ScheduleChannel) {
                ScheduleChannel scheduleChannel = (ScheduleChannel) obj;
                return channel.equals(scheduleChannel.channel) && entries.equals(scheduleChannel.entries);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return channel.hashCode();
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(ScheduleChannel.class).addValue(channel).addValue(entries).toString();
        }
    }

    public static final class ScheduleEntry implements Comparable<ScheduleEntry> {

        private final Item item;
        private final Broadcast broadcast;

        public ScheduleEntry(Item item, Broadcast broadcast) {
            checkNotNull(item);
            checkNotNull(broadcast);
            this.item = item;
            this.broadcast = broadcast;
        }

        @Override
        public int compareTo(ScheduleEntry other) {
            return broadcast.getTransmissionTime().compareTo(other.broadcast.getTransmissionTime());
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (that instanceof ScheduleEntry) {
                ScheduleEntry other = (ScheduleEntry) that;
                return Objects.equal(item, other.item) && Objects.equal(broadcast, other.broadcast);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(item, broadcast);
        }

        public static Function<ScheduleEntry, Item> TO_ITEM = new Function<ScheduleEntry, Item>() {
            @Override
            public Item apply(ScheduleEntry input) {
                return input.item;
            }
        };
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
                Interval broadcastInterval = new Interval(broadcast.getTransmissionTime(), broadcast.getTransmissionEndTime());
                return services.contains(broadcast.getBroadcastOn()) && localInterval.overlaps(broadcastInterval);
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
