package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.ImmutableList;


public class Demographic {

    private String type;
    private List<DemographicSegment> segments;
    
    public Demographic(String type, Iterable<DemographicSegment> segments) {
        this.type = checkNotNull(type);
        this.segments = ImmutableList.copyOf(segments);
    }
    
    public String getType() {
        return type;
    }
    
    public List<DemographicSegment> getSegments() {
        return segments;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (!(that instanceof Demographic)) {
            return false;
        }
        
        Demographic otherDemographic = (Demographic) that;
        
        return this.type == otherDemographic.type;
    }
    
    @Override
    public int hashCode() {
        return type.hashCode();
    }
    
}
