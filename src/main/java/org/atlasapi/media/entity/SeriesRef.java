package org.atlasapi.media.entity;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Objects;

public class SeriesRef extends ChildRef {

    private final Integer seriesNumber;
    
    public SeriesRef(@Nullable Long id, String uri, String sortKey, Integer seriesNumber, DateTime updated) {
        super(id, uri, sortKey, updated, EntityType.SERIES);
        this.seriesNumber = seriesNumber;
    }
    
    public Integer getSeriesNumber() {
        return seriesNumber;
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
}
