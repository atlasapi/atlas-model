package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.Interval;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public final class Schedule {

    private final Interval interval;
    private final Map<String, ScheduleChannel> channelMap;

    private Schedule(Map<String, ScheduleChannel> channelMap, Interval interval) {
        this.channelMap = channelMap;
        this.interval = interval;
    }
    
    public Interval interval() {
        return interval;
    }

    public List<ScheduleChannel> toScheduleChannels() {
        return Lists.newArrayList(channelMap.values());
    }

    public static class ScheduleChannel {
        private final String channel;
        private final List<Item> entries;

        public ScheduleChannel(String channel, Iterable<Item> entries) {
            checkNotNull(channel);
            checkNotNull(entries);
            this.channel = channel;
            this.entries = ImmutableList.copyOf(entries);
        }

        public String channel() {
            return channel;
        }

        public List<Item> items() {
            return entries;
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
