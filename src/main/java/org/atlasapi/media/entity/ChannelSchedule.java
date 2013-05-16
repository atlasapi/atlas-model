package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.util.ItemAndBroadcast;
import org.joda.time.Interval;

import com.google.common.base.Objects;
import com.google.common.collect.Ordering;

public class ChannelSchedule {
    
    private final Channel channel;
    private final Interval interval;
    private final List<ItemAndBroadcast> entries;

    public ChannelSchedule(Channel channel, Interval interval, Iterable<ItemAndBroadcast> entries) {
        this.channel = checkNotNull(channel);
        this.interval = checkNotNull(interval);
        this.entries = Ordering.natural().immutableSortedCopy(entries);
    }

    public Channel getChannel() {
        return channel;
    }
    
    public Interval getInterval() {
        return interval;
    }

    public List<ItemAndBroadcast> getEntries() {
        return entries;
    }
    
    public ChannelSchedule copyWithEntries(Iterable<ItemAndBroadcast> entries) {
        return new ChannelSchedule(channel, interval, entries);
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