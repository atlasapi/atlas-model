package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;
import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public class SeriesRef implements Identifiable, Comparable<SeriesRef> {

    private static final Ordering<SeriesRef> NATURAL = Ordering.natural().reverse();
    
    private final Id id;
    private final String title;
    private final DateTime updated;
    private final Integer seriesNumber;
    
    public static List<SeriesRef> dedupeAndSort(Iterable<SeriesRef> seriesRefs) {
        return NATURAL.immutableSortedCopy(ImmutableSet.copyOf(seriesRefs));
    }
    
    public SeriesRef(Id id, String title, Integer seriesNumber, DateTime updated) {
        this.id = checkNotNull(id);
        this.title =  checkNotNull(title);
        this.updated = updated;
        this.seriesNumber = seriesNumber;
    }
    
    @Nullable
    public Id getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    public DateTime getUpdated() {
        return updated;
    }
    
    public Integer getSeriesNumber() {
        return seriesNumber;
    }
    
    @Override
    public int compareTo(SeriesRef comparableTo) {
        if (seriesNumber != null && comparableTo.seriesNumber != null) {
            return seriesNumber.compareTo(comparableTo.getSeriesNumber());
        } else {
            return title.compareTo(comparableTo.title);
        }
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(id)
                .addValue(seriesNumber)
                .addValue(updated).toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof SeriesRef) {
            SeriesRef other = (SeriesRef) that;
            return id.equals(other.id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    public static Function<SeriesRef, Id> TO_ID = new Function<SeriesRef, Id>() {
        @Override
        public Id apply(SeriesRef input) {
            return input.getId();
        }
    };
}
