package org.atlasapi.media.channel;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

public abstract class TemporalField {
    private final LocalDate startDate;
    private final LocalDate endDate;
    
    public TemporalField(@Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }
}
