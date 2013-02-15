package org.atlasapi.media.content.schedule;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public final class ScheduleRef {

    public static final Builder forChannel(String channelId) {
        return new Builder(checkNotNull(channelId));
    }

    public static final class Builder {

        private String channelId;
        private ImmutableSet.Builder<ScheduleRefEntry> entries;

        private Builder(String channelId) {
            this.channelId = channelId;
            this.entries = ImmutableSet.builder();
        }

        public Builder addEntry(ScheduleRefEntry entry) {
            this.entries.add(entry);
            return this;
        }
        
        public Builder addEntries(Iterable<ScheduleRefEntry> entries) {
            this.entries.addAll(entries);
            return this;
        }
        
        public ScheduleRef build() {
            return new ScheduleRef(channelId, Ordering.natural().immutableSortedCopy(entries.build()));
        }
    }

    private String channelId;
    private ImmutableList<ScheduleRefEntry> entries;

    private ScheduleRef(String channelId, ImmutableList<ScheduleRefEntry> entries) {
        this.channelId = channelId;
        this.entries = entries;
    }
    
    public String getChannelId() {
        return channelId;
    }
    
    public ImmutableList<ScheduleRefEntry> getScheduleEntries() {
        return entries;
    }
    
    public boolean isEmpty() {
        return entries.isEmpty();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ScheduleRef) {
            ScheduleRef other = (ScheduleRef) that;
            return channelId.equals(channelId) && entries.equals(other.entries);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(channelId, entries);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("channel", channelId)
                .add("entries", entries)
                .toString();
    }
    
    public static final class ScheduleRefEntry implements Comparable<ScheduleRefEntry> {
        
        private final Id itemId;
        private final String channelId;
        private final DateTime broadcastTime;
        private final DateTime broadcastEndTime;
        private final Optional<String> broadcastId;

        public ScheduleRefEntry(Long itemId, String channel, DateTime broadcastTime, DateTime broadcastEndTime, @Nullable String broadcastId) {
            this.itemId = Id.valueOf(checkNotNull(itemId));
            this.channelId = checkNotNull(channel);
            this.broadcastTime = checkNotNull(broadcastTime);
            this.broadcastEndTime = checkNotNull(broadcastEndTime);
            this.broadcastId = Optional.fromNullable(broadcastId);
        }

        @Override
        public int compareTo(ScheduleRefEntry o) {
            if (Objects.equal(channelId, o.channelId)) {
                return getBroadcastTime().compareTo(o.getBroadcastTime());
            }
            return 0;
        }

        public Id getItemId() {
            return itemId;
        }

        public String getChannelId() {
            return channelId;
        }

        public DateTime getBroadcastTime() {
            return broadcastTime;
        }
        
        public DateTime getBroadcastEndTime() {
            return broadcastEndTime;
        }

        public Optional<String> getBroadcastId() {
            return broadcastId;
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            }
            if (that instanceof ScheduleRefEntry) {
                ScheduleRefEntry other = (ScheduleRefEntry) that;
                return itemId.equals(other.itemId)
                    && channelId.equals(other.channelId)
                    && broadcastTime.equals(other.broadcastTime);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(itemId, channelId, broadcastTime);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .add("item", itemId)
                    .add("channel", channelId)
                    .add("start", broadcastTime)
                    .toString();
        }

    }
}
