package org.atlasapi.media.channel;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

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
    

    
    public static String valueForDate(Iterable<TemporalString> values, final LocalDate date) {
        TemporalString valueForDate = Iterables.getFirst(Iterables.filter(values, new Predicate<TemporalString>() {
            @Override
            public boolean apply(TemporalString input) {
                if (input.getStartDate() != null) {
                    if (input.getEndDate() != null) {
                        return input.getStartDate().compareTo(date) <= 0
                            && input.getEndDate().compareTo(date) > 0;
                    } else {
                        return input.getStartDate().compareTo(date) <= 0;
                    }
                } else {
                    return true;
                }
            }
        }), null);
        if (valueForDate != null) {
            return valueForDate.getValue();
        }
        return null;
    }
}
