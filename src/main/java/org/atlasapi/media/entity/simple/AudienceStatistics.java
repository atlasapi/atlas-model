package org.atlasapi.media.entity.simple;

import java.util.List;


public class AudienceStatistics {

    private PublisherDetails publisher;
    private long totalViewers;
    private float viewingShare;
    private List<Demographic> demographics;
    
    public List<Demographic> getDemographics() {
        return demographics;
    }
    
    public void setDemographics(List<Demographic> demographics) {
        this.demographics = demographics;
    }

    public PublisherDetails getPublisher() {
        return publisher;
    }
    
    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }
    
    public long getTotalViewers() {
        return totalViewers;
    }
    
    public void setTotalViewers(long totalViewers) {
        this.totalViewers = totalViewers;
    }
    
    public float getViewingShare() {
        return viewingShare;
    }
    
    public void setViewingShare(float viewingShare) {
        this.viewingShare = viewingShare;
    }
    
}
