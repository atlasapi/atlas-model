package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.common.base.Objects;
import com.google.common.collect.Ordering;

public class ChannelNumbering implements Comparable<ChannelNumbering> {
    
    private static final Ordering<HistoricalChannelNumberingEntry> HISTORY_ORDERING = Ordering.natural();
    
    private Channel channel;
    private ChannelGroup channelGroup;
    private String channelNumber;
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
    
    public String getChannelNumber() {
        return channelNumber;
    }
    
    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }   
    
    @XmlElementWrapper(name = "history")
    @XmlElement(name = "historyEntry")
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
    
    @Override
    public int hashCode() {
        return Objects.hashCode(channel, channelNumber, channelGroup, history);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ChannelNumbering) {
            ChannelNumbering other = (ChannelNumbering) that;
            return Objects.equal(channel, other.channel)
                && Objects.equal(channelGroup, other.channelGroup)
                && Objects.equal(channelNumber, other.channelNumber)
                && Objects.equal(history, other.history);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("Channel", channel)
                .add("ChannelGroup", channelGroup)
                .add("ChannelNumber", channelNumber)
                .add("History", history)
                .toString();
    }
}
