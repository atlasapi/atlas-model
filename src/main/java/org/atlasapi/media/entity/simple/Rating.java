package org.atlasapi.media.entity.simple;


public class Rating {

    private PublisherDetails publisherDetails;
    private float value;
    private String type;
    
    public PublisherDetails getPublisherDetails() {
        return publisherDetails;
    }
    
    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisherDetails = publisherDetails;
    }
    
    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
}
