package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Comparator;

import org.atlasapi.media.channel.Channel;
import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;
import com.metabroadcast.common.text.NumberPadder;
import com.metabroadcast.common.time.DateTimeZones;

public class ScheduleEntry implements Comparable<ScheduleEntry> {

    private static final long SECS_FROM_1900 = new Interval(new DateTime(1900, 1, 1, 0, 0, 0, 0, DateTimeZones.UTC), new DateTime(0,DateTimeZones.UTC)).toDurationMillis() / 1000;
    private final Interval interval;
    private final Channel channel;
    private final Publisher publisher;
    private ImmutableSet<ItemRefAndBroadcast> items = ImmutableSet.of();
    private final static Joiner joiner = Joiner.on('|');

    public ScheduleEntry(Interval interval, Channel channel, Publisher publisher, Iterable<ItemRefAndBroadcast> items) {
        Preconditions.checkNotNull(interval);
        Preconditions.checkNotNull(channel);
        Preconditions.checkNotNull(publisher);
        
        this.interval = interval;
        this.channel = channel;
        this.publisher = publisher;
        this.withItems(Ordering.natural().immutableSortedCopy(items));
    }
    
    public Channel channel() {
        return channel;
    }
    
    public Interval interval() {
        return interval;
    }
    
    public ImmutableSet<ItemRefAndBroadcast> getItemRefsAndBroadcasts() {
        return items;
    }
    
    public ScheduleEntry withItems(Iterable<ItemRefAndBroadcast> items) {
        this.items = ImmutableSet.copyOf(items);
        return this;
    }
    
    public Publisher publisher() {
        return publisher;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScheduleEntry) {
            ScheduleEntry entry = (ScheduleEntry) obj;
            return interval.equals(entry.interval) && channel == entry.channel && publisher == entry.publisher && items.equals(entry.items);
        }
        return false;
    }
    
    public String toKey() {
        return toKey(interval, channel, publisher);
    }
    
    public static String toKey(Interval interval, Channel channel, Publisher publisher) {
        return joiner.join(NumberPadder.pad(SECS_FROM_1900 + interval.getStartMillis() / 1000), channel.getKey(), publisher.key());
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(interval, channel, publisher);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(interval).addValue(channel).addValue(publisher).toString();
    }

    @Override
    public int compareTo(ScheduleEntry entry) {
        return interval.getStart().compareTo(entry.interval.getStart());
    }
    
    public final static Comparator<Item> START_TIME_ITEM_COMPARATOR = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            return BROADCAST.apply(item1).getTransmissionTime().compareTo(BROADCAST.apply(item2).getTransmissionTime());
        }
    };
    
    public final static Function<Item, Broadcast> BROADCAST = new Function<Item, Broadcast>() {
        @Override
        public Broadcast apply(Item item) {
            return Iterables.getOnlyElement(item.flattenBroadcasts());
        }
    };
    
    public final static Function<ScheduleEntry, String> KEY = new Function<ScheduleEntry, String>() {
        @Override
        public String apply(ScheduleEntry input) {
            return input.toKey();
        }
    };
    
    public static final class ItemRefAndBroadcast implements Comparable<ItemRefAndBroadcast>{
        
        private final String uri;
        private final Broadcast broadcast;

        public ItemRefAndBroadcast(String uri, Broadcast broadcast) {
            this.uri = checkNotNull(uri);
            this.broadcast = checkNotNull(broadcast);
        }
        
        public ItemRefAndBroadcast(Item item, Broadcast broadcast) {
            this(item.getCanonicalUri(), broadcast);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(uri, broadcast);
        }
        
        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ItemRefAndBroadcast) {
                ItemRefAndBroadcast other = (ItemRefAndBroadcast) obj;
                return uri.equals(other.uri) && broadcast.equals(other.broadcast);
            }
            return false;
        }

        public String getItemUri() {
            return uri;
        }
        
        public Broadcast getBroadcast() {
            return broadcast;
        }
        
        @Override
        public String toString() {
            return String.format("%s: %s", uri, broadcast);
        }
        
        @Override
        public int compareTo(ItemRefAndBroadcast o) {
            return ComparisonChain.start()
                .compare(broadcast.getTransmissionTime(), o.broadcast.getTransmissionEndTime())
                .compare(uri, o.uri)
                .result();
        }
    }
}
