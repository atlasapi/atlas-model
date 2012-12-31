package org.atlasapi.media.channel;

import java.util.Set;

import com.google.common.collect.Sets;

public class Platform extends ChannelGroup {
    private Set<Region> regions = Sets.newHashSet(); 
    
    public void addRegion(Region region) {
        this.regions.add(region);
    }
    
    public void setRegions(Iterable<Region> regions) {
        this.regions = Sets.newHashSet(regions);
    }
    
    public Set<Region> getRegions() {
        return regions;
    }
}
