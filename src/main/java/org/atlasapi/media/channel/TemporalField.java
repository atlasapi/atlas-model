package org.atlasapi.media.channel;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import javax.annotation.Nullable;

import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

public class TemporalField<T> implements Comparable<TemporalField<T>> {
    
    private static final Function<TemporalField<Object>, Object> TO_VALUE = new Function<TemporalField<Object>, Object>() {
        @Override
        public Object apply(TemporalField<Object> input) {
            return input.getValue();
        }
    };
    
    private final T value;
    private final LocalDate startDate;
    private final LocalDate endDate;
    
    public TemporalField(T value, @Nullable LocalDate startDate, @Nullable LocalDate endDate) {
        this.value = checkNotNull(value);
        this.startDate = startDate;
        this.endDate = endDate;
    }
    
    public T getValue() {
        return value;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
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
        if (that instanceof TemporalField<?>) {
            TemporalField<?> other = (TemporalField<?>) that;
            return value.equals(other.value) 
                && Objects.equal(startDate,  other.startDate) 
                && Objects.equal(endDate,  other.endDate);
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
    
    public static <T> T currentOrFutureValue(Iterable<TemporalField<T>> values) {
        final LocalDate now = new LocalDate();
        Set<T> currentValues = TemporalField.<T>currentValues(values);
        if (!currentValues.isEmpty()) {
            return Iterables.getOnlyElement(currentValues);
        }

        // if no current title, return first future title
        TemporalField<T> nextValue = Iterables.getFirst(
            Ordering.natural().immutableSortedCopy(Iterables.filter(
                values, 
                new Predicate<TemporalField<T>>() {
                    @Override
                    public boolean apply(TemporalField<T> input) {
                        return input.getStartDate() != null && input.getStartDate().isAfter(now);
                    }
                }
            )), 
            null
        );
        if (nextValue != null) {
            return nextValue.getValue();
        }
        return null;
    }

    public static <T> Set<T> currentValues(Iterable<TemporalField<T>> values) {
        final LocalDate now = new LocalDate();
        return TemporalField.<T>valuesForDate(values, now);
    }
    
    public static <T> Set<T> valuesForDate(Iterable<TemporalField<T>> values, final LocalDate date) {
        Iterable<TemporalField<T>> filtered = Iterables.filter(
                values, 
                new Predicate<TemporalField<T>>() {
                    @Override
                    public boolean apply(TemporalField<T> input) {
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
                }
            );
        return ImmutableSet.copyOf(Iterables.transform(filtered, TemporalField.<T>toValueFunction()));
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    private static <T> Function<TemporalField<T>, T> toValueFunction() {
        return (Function) TO_VALUE;
    }

    @Override
    public int compareTo(TemporalField<T> that) {
        if (this == that) {
            return 0;
        }

        if (this.getStartDate() != null) {
            if (that.getStartDate() != null) {
                return this.getStartDate().compareTo(that.getStartDate());
            } else {
                return 1;
            }
        } else {
            if (that.getStartDate() != null) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
