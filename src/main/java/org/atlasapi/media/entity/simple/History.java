package org.atlasapi.media.entity.simple;

import java.util.Date;
import java.util.Set;

import org.joda.time.LocalDate;

import com.google.common.collect.ImmutableSet;

public class History {
    private Date startDate;
    private Date endDate;
    private Set<TemporalString> title;
    private Set<TemporalString> image;

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
    public Set<TemporalString> getTitle() {
        return title;
    }
    public void setTitle(Iterable<TemporalString> title) {
        this.title = ImmutableSet.copyOf(title);
    }
    public Set<TemporalString> getImage() {
        return image;
    }
    public void setImage(Iterable<TemporalString> image) {
        this.image = ImmutableSet.copyOf(image);
    }
}
