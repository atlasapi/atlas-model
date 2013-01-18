package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.common.Id;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Platform extends ChannelGroup {
    
    private Set<Id> regions = Sets.newHashSet(); 
    
    public void addRegion(Id regionId) {
        this.regions.add(regionId);
    }
    
    public void addRegion(Region region) {
        this.regions.add(region.getId());
    }
    
    public void setRegions(Iterable<Region> regions) {
        this.regions.clear();
        for (Region region : regions) {
            addRegion(region);
        }
    }
    
    public void setRegionIds(Iterable<Id> regionIds) {
        this.regions = Sets.newHashSet(regionIds);        
    }
    
    public Set<Id> getRegions() {
        return ImmutableSet.copyOf(regions);
    }
}
