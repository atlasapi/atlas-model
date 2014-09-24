package org.atlasapi.media.channel;

import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Platform extends ChannelGroup {
    private Set<Long> regions = Sets.newHashSet(); 
    
    public void addRegion(Long regionId) {
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
    
    public void setRegionIds(Iterable<Long> regionIds) {
        this.regions = Sets.newHashSet(regionIds);        
    }
    
    public Set<Long> getRegions() {
        return ImmutableSet.copyOf(regions);
    }

    @Override
    ChannelGroup copy() {
        Platform platform = new Platform();
        
        copyTo(this, platform);
        platform.regions = Sets.newHashSet(this.regions);
        
        return platform;
    }
}
