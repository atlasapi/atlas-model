package org.atlasapi.search.model;

import com.google.common.base.Objects;

public class Search {

    private final String query;

    public Search(String query) {
        this.query = query;
    }
    
    public String query() {
        return query;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Search) {
            Search target = (Search) obj;
            return Objects.equal(query, target.query);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(query);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(query).toString();
    }
}
