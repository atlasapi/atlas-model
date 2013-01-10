package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.atlasapi.media.channel.Channel;
import org.joda.time.Interval;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public final class Schedule {

    private final Interval interval;
    private final List<ChannelSchedule> channelSchedules;
    
    public static Schedule fromChannelMap(Map<Channel, List<Item>> channelMap, Interval interval) {
        ImmutableList.Builder<ChannelSchedule> scheduleChannels = ImmutableList.builder();
        for (Entry<Channel, List<Item>> channel: channelMap.entrySet()) {
            scheduleChannels.add(new ChannelSchedule(channel.getKey(), interval, channel.getValue()));
        }
        return new Schedule(scheduleChannels.build(), interval);
    }

    public Schedule(List<ChannelSchedule> channelSchedules, Interval interval) {
        this.channelSchedules = channelSchedules;
        this.interval = interval;
    }
    
    public Interval interval() {
        return interval;
    }

    public List<ChannelSchedule> channelSchedules() {
        return this.channelSchedules;
    }

    @Override
    public int hashCode() {
        return channelSchedules.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Schedule) {
            Schedule other = (Schedule) that;
            return channelSchedules.equals(other.channelSchedules);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .addValue(channelSchedules)
            .toString();
    }
}
