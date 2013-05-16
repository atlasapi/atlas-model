package org.atlasapi.media.entity;

import java.util.List;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public class SeriesRef implements Comparable<SeriesRef> {

    private static final Ordering<SeriesRef> NATURAL = Ordering.natural().reverse();
    
    private final Long id;
    private final String uri;
    private final String title;
    private final DateTime updated;
    private final Integer seriesNumber;
    
    public static List<SeriesRef> dedupeAndSort(Iterable<SeriesRef> seriesRefs) {
        return NATURAL.immutableSortedCopy(ImmutableSet.copyOf(seriesRefs));
    }
    
    public SeriesRef(@Nullable Long id, String uri, String title, Integer seriesNumber, DateTime updated) {
        this.id = id;
        this.uri = Preconditions.checkNotNull(uri);
        this.title =  Preconditions.checkNotNull(title);
        this.updated = updated;
        this.seriesNumber = seriesNumber;
    }
    
    @Nullable
    public Long getId() {
        return id;
    }
    
    public String getUri() {
        return uri;
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
                .addValue(getUri())
                .addValue(seriesNumber)
                .addValue(getUpdated()).toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof SeriesRef) {
            SeriesRef other = (SeriesRef) that;
            return this.getUri().equals(other.getUri());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.getUri().hashCode();
    }
    
    public static Function<SeriesRef, String> TO_URI = new Function<SeriesRef, String>() {
        @Override
        public String apply(SeriesRef input) {
            return input.getUri();
        }
    };
}
