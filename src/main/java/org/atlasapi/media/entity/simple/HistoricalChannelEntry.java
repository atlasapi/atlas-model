package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class HistoricalChannelEntry implements Comparable<HistoricalChannelEntry> {
    
    private Date startDate;
    private String title;
    private String image;

    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDate();
        }
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getImage() {
        return image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(startDate, title, image);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof HistoricalChannelEntry) {
            HistoricalChannelEntry entry = (HistoricalChannelEntry)that;
            return startDate.equals(entry.startDate)
                && Objects.equal(title, entry.title)
                && Objects.equal(image, entry.image);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("startDate", startDate)
            .add("title", title)
            .add("image", image)
            .toString();
    }

    @Override
    public int compareTo(HistoricalChannelEntry that) {
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
