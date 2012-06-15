package org.atlasapi.media.content;

import org.joda.time.DateTime;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class SeriesRef {

    private final String uri;
    private final Integer seriesNumber;
    private final DateTime updated;
    
    public SeriesRef(String uri, Integer seriesNumber, DateTime updated) {
        Preconditions.checkNotNull(uri);
        Preconditions.checkNotNull(seriesNumber);
        Preconditions.checkNotNull(updated);
        this.uri = uri;
        this.seriesNumber = seriesNumber;
        this.updated = updated;
    }
    
    public String getUri() {
        return uri;
    }
    
    public Integer getSeriesNumber() {
        return seriesNumber;
    }
    
    public DateTime getUpdated() {
        return updated;
    }
    
    @Override
    public boolean equals(Object obj) {
        return uri.equals(uri);
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(uri).addValue(seriesNumber).addValue(updated).toString();
    }
}
