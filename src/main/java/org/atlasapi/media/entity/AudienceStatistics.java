package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableSet;


public class AudienceStatistics {

    private Long totalViewers;
    private Float viewingShare;
    private Set<Demographic> demographics;
    
    public AudienceStatistics(@Nullable Long totalViewers, 
            @Nullable Float viewingShare, 
            Iterable<Demographic> demographics) {
        
        this.totalViewers = totalViewers;
        this.viewingShare = viewingShare;
        this.demographics = ImmutableSet.copyOf(demographics);
        
    }
    
    public Set<Demographic> getDemographics() {
        return demographics;
    }
    
    public Long getTotalViewers() {
        return totalViewers;
    }
    
    public Float getViewingShare() {
        return viewingShare;
    }
    
}
