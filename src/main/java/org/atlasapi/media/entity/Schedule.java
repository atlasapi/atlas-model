package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.Interval;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public final class Schedule {

    private final Interval interval;
    private final List<ScheduleChannel> scheduleChannels;
    
    public static Schedule fromChannelMap(Map<Channel, List<Item>> channelMap, Interval interval) {
        ImmutableList.Builder<ScheduleChannel> scheduleChannels = ImmutableList.builder();
        for (Entry<Channel, List<Item>> channel: channelMap.entrySet()) {
            scheduleChannels.add(new ScheduleChannel(channel.getKey(), channel.getValue()));
        }
        return new Schedule(scheduleChannels.build(), interval);
    }

    public Schedule(List<ScheduleChannel> scheduleChannels, Interval interval) {
        this.scheduleChannels = scheduleChannels;
        this.interval = interval;
    }
    
    public Interval interval() {
        return interval;
    }

    public List<ScheduleChannel> scheduleChannels() {
        return this.scheduleChannels;
    }

    public static class ScheduleChannel {
        private final Channel channel;
        private final List<Item> entries;

        public ScheduleChannel(Channel channel, Iterable<Item> entries) {
            checkNotNull(channel);
            checkNotNull(entries);
            this.channel = channel;
            this.entries = ImmutableList.copyOf(entries);
        }

        public Channel channel() {
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
        return scheduleChannels.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Schedule) {
            return scheduleChannels.equals(((Schedule) obj).scheduleChannels);
        }
        return false;
    }
}
