package org.atlasapi.media.entity.simple;

import java.util.List;


public class AudienceStatistics {

    private PublisherDetails publisher;
    private Long totalViewers;
    private Float viewingShare;
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
    
    public Long getTotalViewers() {
        return totalViewers;
    }
    
    public void setTotalViewers(Long totalViewers) {
        this.totalViewers = totalViewers;
    }
    
    public Float getViewingShare() {
        return viewingShare;
    }
    
    public void setViewingShare(Float viewingShare) {
        this.viewingShare = viewingShare;
    }
    
}
