package org.atlasapi.media.entity.simple;

import java.util.List;


public class Demographic {

    private String type;
    private List<DemographicSegment> segments;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<DemographicSegment> getSegments() {
        return segments;
    }
    
    public void setSegments(List<DemographicSegment> segments) {
        this.segments = segments;
    }
    
}
