package org.atlasapi.media.channel;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;

public class TemporalString extends TemporalField {
    
    private final String value;

    public String getValue() {
        return value;
    }

    public TemporalString(String value, @Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        super(startDate, endDate);
        checkNotNull(value);
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getStartDate(), getEndDate(), value);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof TemporalString) {
            TemporalString other = (TemporalString) that;
            return other.value.equals(other.value) 
                && Objects.equal(getStartDate(),  other.getStartDate()) 
                && Objects.equal(getEndDate(),  other.getEndDate());
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("Value", value)
            .add("Start Date", getStartDate())
            .add("End Date", getEndDate())
            .toString();
    }
}
