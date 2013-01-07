package org.atlasapi.media.channel;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Objects;

public class ChannelNumbering {

    private Integer channelNumber;
    private Long channel;
    private Long channelGroup;
    private DateTime startDate;
    private DateTime endDate;

    public static Builder builder() {
        return new Builder();
    }

    private ChannelNumbering(@Nullable Integer channelNumber, Long channel, Long channelGroup, DateTime startDate,
            DateTime endDate) {
        this.channel = checkNotNull(channel);
        this.channelNumber = channelNumber;
        this.channelGroup = checkNotNull(channelGroup);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getChannelNumber() {
        return channelNumber;
    }

    public Long getChannel() {
        return channel;
    }

    public Long getChannelGroup() {
        return channelGroup;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setChannelNumber(Integer channelNumber) {
        this.channelNumber = channelNumber;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }

    public void setChannelGroup(Long channelGroup) {
        this.channelGroup = channelGroup;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(channel, channelGroup, startDate, endDate);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ChannelNumbering) {
            ChannelNumbering other = (ChannelNumbering) that;
            return other.channel.equals(channel) 
                && other.channelGroup.equals(channelGroup)
                && other.startDate.equals(startDate) 
                && other.endDate.equals(endDate);
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
        private Integer channelNumber;
        private Long channel;
        private Long channelGroup;
        private DateTime startDate;
        private DateTime endDate;

        private Builder() {
        }

        public ChannelNumbering build() {
            return new ChannelNumbering(channelNumber, channel, channelGroup, startDate, endDate);
        }

        public Builder withChannelNumber(Integer channelNumber) {
            this.channelNumber = channelNumber;
            return this;
        }

        public Builder withChannel(Channel channel) {
            this.channel = channel.getId();
            return this;
        }

        public Builder withChannel(Long channelId) {
            this.channel = channelId;
            return this;
        }

        public Builder withChannelGroup(ChannelGroup channelGroup) {
            this.channelGroup = channelGroup.getId();
            return this;
        }

        public Builder withChannelGroup(Long channelGroupId) {
            this.channelGroup = channelGroupId;
            return this;
        }

        public Builder withStartDate(DateTime startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(DateTime endDate) {
            this.endDate = endDate;
            return this;
        }
    }
}
