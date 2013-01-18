package org.atlasapi.media.channel;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class ChannelNumbering {
    
    public static final Function<ChannelNumbering, Id> TO_CHANNEL_GROUP = new Function<ChannelNumbering, Id>() { 
        @Override
        public Id apply(ChannelNumbering input) {
            return input.channelGroup;
        }
    };
    
    public static final Function<ChannelNumbering, Id> TO_CHANNEL = new Function<ChannelNumbering, Id>() { 
        @Override
        public Id apply(ChannelNumbering input) {
            return input.channel;
        }
    };

    public static Iterable<ChannelNumbering> CURRENT_NUMBERINGS(Iterable<ChannelNumbering> numberings) {
        final LocalDate now = new LocalDate();
        return Iterables.filter(numberings, new Predicate<ChannelNumbering>() {
            @Override
            public boolean apply(ChannelNumbering input) {
                if (input.getStartDate() != null) {
                    if (input.getEndDate() != null) {
                        return input.getStartDate().compareTo(now) <= 0
                            && input.getEndDate().compareTo(now) > 0;
                    } else {
                        return input.getStartDate().compareTo(now) <= 0;
                    }
                } else {
                    return true;
                }
            }
        });
    }

    private String channelNumber;
    private Id channel;
    private Id channelGroup;
    private LocalDate startDate;
    private LocalDate endDate;

    public static Builder builder() {
        return new Builder();
    }

    private ChannelNumbering(@Nullable String channelNumber, Id channel, Id channelGroup, @Nullable LocalDate startDate,
            @Nullable LocalDate endDate) {
        this.channel = channel;
        this.channelNumber = channelNumber;
        this.channelGroup = checkNotNull(channelGroup);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getChannelNumber() {
        return channelNumber;
    }

    public Id getChannel() {
        return channel;
    }

    public Id getChannelGroup() {
        return channelGroup;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }

    public void setChannel(Id channel) {
        this.channel = channel;
    }

    public void setChannelGroup(Id channelGroup) {
        this.channelGroup = channelGroup;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(channel, channelNumber, channelGroup, startDate, endDate);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ChannelNumbering) {
            ChannelNumbering other = (ChannelNumbering) that;
            return Objects.equal(other.channel, channel) 
                && other.channelGroup.equals(channelGroup)
                && Objects.equal(channelNumber, other.channelNumber)
                && Objects.equal(startDate, other.startDate) 
                && Objects.equal(endDate,  other.endDate);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("Channel", channel)
            .add("ChannelGroup", channelGroup)
            .add("ChannelNumber", channelNumber)
            .add("Start Date", startDate)
            .add("End Date", endDate)
            .toString();
    }

    public static class Builder {
        
        private String channelNumber;
        private Id channel;
        private Id channelGroup;
        private LocalDate startDate;
        private LocalDate endDate;

        private Builder() {
        }

        public ChannelNumbering build() {
            return new ChannelNumbering(channelNumber, channel, channelGroup, startDate, endDate);
        }

        public Builder withChannelNumber(String channelNumber) {
            this.channelNumber = channelNumber;
            return this;
        }

        public Builder withChannel(Channel channel) {
            this.channel = channel.getId();
            return this;
        }

        public Builder withChannel(Id channelId) {
            this.channel = channelId;
            return this;
        }

        public Builder withChannelGroup(ChannelGroup channelGroup) {
            this.channelGroup = channelGroup.getId();
            return this;
        }

        public Builder withChannelGroup(Id channelGroupId) {
            this.channelGroup = channelGroupId;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }
    }
}
