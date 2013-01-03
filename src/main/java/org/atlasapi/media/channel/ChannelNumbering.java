package org.atlasapi.media.channel;

import org.joda.time.DateTime;

public class ChannelNumbering {
    private final int channelNumber;
    private final Channel channel;
    private final ChannelGroup channelGroup;
    private final DateTime startDate;
    private final DateTime endDate;
    
    public static Builder builder() {
        return new Builder();
    }
    
    private ChannelNumbering(int channelNumber, Channel channel, ChannelGroup channelGroup, DateTime startDate,
            DateTime endDate) {
        this.channelNumber = channelNumber;
        this.channel = channel;
        this.channelGroup = channelGroup;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getChannelNumber() {
        return channelNumber;
    }
    
    public Channel getChannel() {
        return channel;
    }
    
    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }
    
    public DateTime getStartDate() {
        return startDate;
    }
    
    public DateTime getEndDate() {
        return endDate;
    }
    
    public static class Builder {
        private int channelNumber;
        private Channel channel;
        private ChannelGroup channelGroup;
        private DateTime startDate;
        private DateTime endDate;
        
        private Builder(){}
        
        public ChannelNumbering build() {
            return new ChannelNumbering(channelNumber, channel, channelGroup, startDate, endDate);
        }

        public Builder withChannelNumber(int channelNumber) {
            this.channelNumber = channelNumber;
            return this;
        }

        public Builder withChannel(Channel channel) {
            this.channel = channel;
            return this;
        }

        public Builder withChannelGroup(ChannelGroup channelGroup) {
            this.channelGroup = channelGroup;
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
