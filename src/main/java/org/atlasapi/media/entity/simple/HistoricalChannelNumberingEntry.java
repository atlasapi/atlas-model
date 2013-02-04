package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class HistoricalChannelNumberingEntry implements Comparable<HistoricalChannelNumberingEntry> {

    private Date startDate;
    private Date endDate;
    private String channelNumber;

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDate();
        }
    }
    
    public void setEndDate(LocalDate endDate) {
        if (endDate != null) {
            this.endDate = endDate.toDate();
        }
    }

    public String getChannelNumber() {
        return channelNumber;
    }
    
    public void setChannelNumber(String channelNumber) {
        this.channelNumber = channelNumber;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(startDate, endDate, channelNumber);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof HistoricalChannelNumberingEntry) {
            HistoricalChannelNumberingEntry entry = (HistoricalChannelNumberingEntry)that;
            return startDate.equals(entry.startDate)
                && Objects.equal(endDate, entry.endDate)
                && Objects.equal(channelNumber, entry.channelNumber);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("startDate", startDate)
            .add("endDate", endDate)
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
                if (startDate.equals(that.startDate)) {
                    if (endDate != null) {
                        if (that.endDate != null) {
                            return endDate.compareTo(that.endDate);
                        }
                        return 1;
                    }
                    if (that.endDate != null) {
                        return -1;
                    }
                    return 0;
                }
                return startDate.compareTo(that.startDate);
            }
            return 1;
        }
        if (that.startDate != null) {
            return -1;
        }
        return 0;
    }
}
