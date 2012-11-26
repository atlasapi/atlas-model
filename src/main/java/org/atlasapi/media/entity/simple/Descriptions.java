package org.atlasapi.media.entity.simple;


public class Descriptions {
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public void setMediumDescription(String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }
    
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public String getMediumDescription() {
        return mediumDescription;
    }
    
    public String getLongDescription() {
        return longDescription;
    }
}
