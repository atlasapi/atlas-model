package org.atlasapi.media.entity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.metabroadcast.common.time.DateTimeZones;

public class ScheduleEntry implements Comparable<ScheduleEntry> {

    private static final long MILLIS_FROM_1900 = 2208988800000L;
    private final Interval interval;
    private final Channel channel;
    private final Publisher publisher;
    private ImmutableList<Item> items = ImmutableList.of();
    private final static Joiner joiner = Joiner.on('|');
    
    public static List<ScheduleEntry> from(Iterable<Item> items) {
        Map<String, ScheduleEntry> entries = Maps.newHashMap();
        
        for (Item item: items) {
            for (Version version: item.getVersions()) {
                for (Broadcast broadcast: version.getBroadcasts()) {
                    Version entryVersion = version.copy();
                    entryVersion.setBroadcasts(ImmutableSet.of(broadcast.copy()));
                    Item entryItem = (Item) item.copy();
                    entryItem.setVersions(ImmutableSet.of(entryVersion));
                    
                    Channel channel = Channel.fromUri(broadcast.getBroadcastOn()).requireValue();
                    Publisher publisher = item.getPublisher();
                    long hourMillis = ((int) broadcast.getTransmissionTime().getMillis() / 3600000) * 3600000;
                    long endMillis = broadcast.getTransmissionEndTime().getMillis();
                    
                    while (hourMillis < endMillis) {
                        Interval interval = new Interval(new DateTime(hourMillis, DateTimeZones.UTC), new DateTime(hourMillis+3599999, DateTimeZones.UTC));
                        String key = toKey(interval, channel, publisher);
                        
                        if (entries.containsKey(key)) {
                            ScheduleEntry entry = entries.get(key);
                            entry.withItems(ImmutableList.<Item>builder().addAll(entry.items()).add(entryItem).build());
                        } else {
                            ScheduleEntry entry = new ScheduleEntry(interval, channel, publisher, ImmutableList.of(entryItem));
                            entries.put(key, entry);
                        }
                        hourMillis+= (3600000);
                    }
                }
            }
        }
        
        return Ordering.natural().immutableSortedCopy(entries.values());
    }

    public ScheduleEntry(Interval interval, Channel channel, Publisher publisher, Iterable<Item> items) {
        Preconditions.checkNotNull(interval);
        Preconditions.checkNotNull(channel);
        Preconditions.checkNotNull(publisher);
        
        this.interval = interval;
        this.channel = channel;
        this.publisher = publisher;
        this.withItems(items);
    }
    
    public Channel channel() {
        return channel;
    }
    
    public Interval interval() {
        return interval;
    }
    
    public ImmutableList<Item> items() {
        return items;
    }
    
    public ScheduleEntry withItems(Iterable<Item> items) {
        for (Item item: items) {
            Preconditions.checkNotNull(BROADCAST.apply(item));
        }
        this.items = ImmutableList.copyOf(items);
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
        return joiner.join(paddedMillis(interval), channel.key(), publisher.key());
    }
    
    private static String paddedMillis(Interval interval) {
        String key = String.valueOf(MILLIS_FROM_1900 + interval.getStartMillis());
        return String.format("%1$-16s", key).replace(' ', '0');
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
            return Iterables.getOnlyElement(Iterables.getOnlyElement(item.getVersions()).getBroadcasts());
        }
    };
}
