package org.atlasapi.media.entity.simple;


public class LocalisedDescription {

    private String locale;
    private String title;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private String description;
    
    public LocalisedDescription() {}
    
    public String getLocale() {
        return locale;
    }
    
    public void setLocale(String locale) {
        this.locale = locale;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getShortDescription() {
        return shortDescription;
    }
    
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }
    
    public String getMediumDescription() {
        return mediumDescription;
    }
    
    public void setMediumDescription(String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }
    
    public String getLongDescription() {
        return longDescription;
    }
    
    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
}
