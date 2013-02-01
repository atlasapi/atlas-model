package org.atlasapi.media.entity.simple;

import java.util.List;

import com.google.common.collect.Ordering;


public class ChannelNumbering implements Comparable<ChannelNumbering> {
    
    private static final Ordering<HistoricalChannelNumberingEntry> HISTORY_ORDERING = Ordering.natural();
    
    private Channel channel;
    private ChannelGroup channelGroup;
    private Integer channelNumber;
    private List<HistoricalChannelNumberingEntry> history; 
    
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
    
    public List<HistoricalChannelNumberingEntry> getHistory() {
        return history;
    }

    public void setHistory(Iterable<HistoricalChannelNumberingEntry> history) {
        this.history = HISTORY_ORDERING.immutableSortedCopy(history);
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
