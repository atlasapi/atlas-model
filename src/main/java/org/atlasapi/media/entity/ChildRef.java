package org.atlasapi.media.entity;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public class ChildRef implements Comparable<String> {

    private final String uri;
    private final String sortKey;
    
    public ChildRef(String uri, String sortKey) {
        Preconditions.checkNotNull(uri);
        Preconditions.checkNotNull(sortKey);
        this.uri = uri;
        this.sortKey = sortKey;
    }
    
    public String getUri() {
        return uri;
    }
    
    public String getSortKey() {
        return sortKey;
    }
    
    @Override
    public boolean equals(Object obj) {
        return uri.equals(uri) && sortKey.equals(sortKey);
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(uri).addValue(sortKey).toString();
    }

    @Override
    public int compareTo(String comparableTo) {
        return sortKey.compareTo(comparableTo);
    }
}
