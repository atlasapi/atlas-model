package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class HistoricalChannelNumberingEntry implements Comparable<HistoricalChannelNumberingEntry> {

    private Date startDate;
    private Integer channelNumber;

    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDate();
        }
    }

    public Integer getChannelNumber() {
        return channelNumber;
    }
    
    public void setChannelNumber(Integer channelNumber) {
        this.channelNumber = channelNumber;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(startDate, channelNumber);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof HistoricalChannelNumberingEntry) {
            HistoricalChannelNumberingEntry entry = (HistoricalChannelNumberingEntry)that;
            return startDate.equals(entry.startDate)
                && Objects.equal(channelNumber, entry.channelNumber);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("startDate", startDate)
            .add("channelNumber", channelNumber)
            .toString();
    }

    @Override
    public int compareTo(HistoricalChannelNumberingEntry that) {
        if (this == that) {
            return 0;
        }
        
        if (startDate != null) {
            if (that.startDate != null) {
                return startDate.compareTo(that.startDate);
            } else {
                return 1;
            }
        } else {
            if (that.startDate != null) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
