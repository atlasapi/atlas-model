package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.joda.time.LocalDate;

public class TemporalString {
    private String value;
    private Date startDate;
    private Date endDate;

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
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
}
