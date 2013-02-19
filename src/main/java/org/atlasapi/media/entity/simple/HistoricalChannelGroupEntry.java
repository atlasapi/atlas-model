package org.atlasapi.media.entity.simple;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class HistoricalChannelGroupEntry implements Comparable<HistoricalChannelGroupEntry> {
    
    private Date startDate;
    private String title;

    @XmlElement(name = "startDate")
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDateTimeAtStartOfDay(DateTimeZone.UTC).toDate();
        }
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(startDate, title);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof HistoricalChannelGroupEntry) {
            HistoricalChannelGroupEntry entry = (HistoricalChannelGroupEntry)that;
            return startDate.equals(entry.startDate)
                && Objects.equal(title, entry.title);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("startDate", startDate)
            .add("title", title)
            .toString();
    }

    @Override
    public int compareTo(HistoricalChannelGroupEntry that) {
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
