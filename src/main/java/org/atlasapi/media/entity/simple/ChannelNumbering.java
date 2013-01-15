package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.joda.time.LocalDate;

public class ChannelNumbering implements Comparable<ChannelNumbering> {
    Channel channel;
    ChannelGroup channelGroup;
    Integer channelNumber;
    Date startDate;
    Date endDate;
    
    public Channel getChannel() {
        return channel;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }
    public void setChannelGroup(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
    }
    public Integer getChannelNumber() {
        return channelNumber;
    }
    public void setChannelNumber(Integer channelNumber) {
        this.channelNumber = channelNumber;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDate();
        }
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        if (endDate != null) {
            this.endDate = endDate.toDate();
        }
    }
    
    @Override
    public int compareTo(ChannelNumbering that) {
        if (this == that) {
            return 0;
        }
        
        if (channelNumber != null) {
            if (that.channelNumber != null) {
                return channelNumber.compareTo(that.channelNumber);
            } else {
                return 1;
            }
        } else {
            if (that.channelNumber != null) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
