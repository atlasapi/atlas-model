package org.atlasapi.media.content.item;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;

public final class Broadcast extends Identified {
    
    public static Builder builder(Channel channel, DateTime transmissionStartTime, DateTime transmissionEndTime) {
        checkNotNull(channel, "Null channel creating broadcast");
        return builder(channel.id(), transmissionStartTime, transmissionEndTime);
    }
    
    public static Builder builder(Channel channel, DateTime transmissionStartTime, Duration duration) {
        checkNotNull(channel, "Null channel creating broadcast");
        return builder(channel.id(), transmissionStartTime, duration);
    }

    public static Builder builder(Long channel, DateTime transmissionStartTime, DateTime transmissionEndTime) {
        checkNotNull(channel, "Null channel id creating broadcast");
        Preconditions.checkArgument(transmissionStartTime.isBefore(transmissionEndTime), "Transmission end must be after transmission start");
        return new Builder(channel, transmissionStartTime, transmissionEndTime);
    }

    public static Builder builder(Long channel, DateTime transmissionStartTime, Duration broadcastDuration) {
        Preconditions.checkArgument(channel != null);
        Preconditions.checkArgument(broadcastDuration.isLongerThan(Duration.ZERO), "Broadcast duration must be greater than zero");
        return new Builder(channel, transmissionStartTime, broadcastDuration);
    }

    public static final class Builder extends Identified.Builder<Broadcast, Builder> {

        private DateTime transmissionStartTime;
        private DateTime transmissionEndTime;
        private Integer broadcastDuration;
        private Long channel;
        private LocalDate scheduleDate;
        private Boolean activelyPublished;
        private BroadcastFlags flags;

        private Builder(Long channel, DateTime transmissionStartTime, DateTime transmissionEndTime) {
            this(channel, transmissionStartTime, transmissionEndTime, new Duration(transmissionStartTime, transmissionEndTime));
        }

        private Builder(Long channel, DateTime transmissionStartTime, Duration broadcastDuration) {
            this(channel, transmissionStartTime, transmissionStartTime.plus(broadcastDuration), broadcastDuration);
        }

        private Builder(Long channel, DateTime transmissionStartTime, DateTime transmissionEndTime, Duration broadcastDuration) {
            this.channel = channel;
            this.transmissionStartTime = transmissionStartTime;
            this.transmissionEndTime = transmissionEndTime;
            this.broadcastDuration = Ints.saturatedCast(broadcastDuration.getStandardSeconds());
        }

        private Builder() {
        }

        @Override
        public Builder copy(Broadcast broadcast) {
            super.copy(broadcast);
            this.transmissionStartTime = broadcast.transmissionStartTime;
            this.transmissionEndTime = broadcast.transmissionEndTime;
            this.broadcastDuration = broadcast.broadcastDuration;
            this.channel = broadcast.channelId;
            this.scheduleDate = broadcast.scheduleDate;
            this.activelyPublished = broadcast.activelyPublished;
            this.flags = broadcast.flags;
            return builder();
        }

        public Builder withscheduleDate(LocalDate scheduleDate) {
            this.scheduleDate = scheduleDate;
            return builder();
        }

        public Builder withactivelyPublished(Boolean activelyPublished) {
            this.activelyPublished = activelyPublished;
            return builder();
        }

        public Builder withFlags(BroadcastFlags flags) {
            this.flags = flags;
            return builder();
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Broadcast build() {
            return new Broadcast(this);
        }
    }
    private final DateTime transmissionStartTime;
    private final DateTime transmissionEndTime;
    private final Integer broadcastDuration;
    private final Long channelId;
    private final LocalDate scheduleDate;
    private final Boolean activelyPublished;
    private final BroadcastFlags flags;

    public Broadcast(Builder b) {
        super(b);
        this.transmissionStartTime = b.transmissionStartTime;
        this.transmissionEndTime = b.transmissionEndTime;
        this.broadcastDuration = b.broadcastDuration;
        this.channelId = b.channel;
        this.scheduleDate = b.scheduleDate;
        this.activelyPublished = b.activelyPublished;
        this.flags = b.flags;
    }

    public Builder copy() {
        return new Builder().copy(this);
    }

    public DateTime transmissionTime() {
        return this.transmissionStartTime;
    }

    public DateTime transmissionEndTime() {
        return transmissionEndTime;
    }

    public Integer broadcastDuration() {
        return this.broadcastDuration;
    }

    public Long broadcastOn() {
        return channelId;
    }

    public LocalDate scheduleDate() {
        return scheduleDate;
    }

    public Boolean isActivelyPublished() {
        return activelyPublished;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Broadcast) {
            Broadcast other = (Broadcast) that;
            if (sourceUri != null && other.sourceUri != null) {
                return sourceUri.equals(other.sourceUri);
            }
            return channelId.equals(other.channelId)
                    && transmissionStartTime.equals(other.transmissionStartTime)
                    && transmissionEndTime.equals(other.transmissionEndTime);
        }
        return false;
    }

    @Override
    public int hashCode() {
        // Currently publishers either have ids for all broadcasts or all broadcasts don't have ids 
        // (there are no mixes of broadcasts with and without ids) so this hashCode is safe
        if (sourceUri != null) {
            return sourceUri.hashCode();
        }
        if (transmissionStartTime != null) {
            return transmissionStartTime.hashCode();
        }
        return 43;
    }

    @Override
    public BroadcastIdentifier toIdentifier() {
        return new BroadcastIdentifier(this);
    }

    public static final class BroadcastIdentifier extends Identifier {

        public BroadcastIdentifier(Broadcast broadcast) {
            super(broadcast);
        }
    }
}
