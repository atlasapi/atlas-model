package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.atlasapi.media.channel.Channel;
import org.joda.time.Interval;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

public class ChannelSchedule {
    
    private final Channel channel;
    private final Interval interval;
    private final List<Item> entries;

    public ChannelSchedule(Channel channel, Interval interval, Iterable<Item> entries) {
        this.channel = checkNotNull(channel);
        this.interval = checkNotNull(interval);
        this.entries = ImmutableList.copyOf(entries);
    }

    public Channel channel() {
        return channel;
    }
    
    public Interval interval() {
        return interval;
    }

    public List<Item> items() {
        return entries;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ChannelSchedule) {
            ChannelSchedule scheduleChannel = (ChannelSchedule) obj;
            return channel.equals(scheduleChannel.channel)
                && interval.equals(interval)
                && entries.equals(scheduleChannel.entries);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(channel, interval);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(ChannelSchedule.class)
            .add("channel", channel)
            .add("interval", interval)
            .add("entries", entries)
            .toString();
    }
}