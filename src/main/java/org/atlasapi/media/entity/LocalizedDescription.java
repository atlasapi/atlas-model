package org.atlasapi.media.entity;

import com.google.common.base.Objects;


/**
 * A localized description of a piece of content
 */
public class LocalizedDescription extends Localized {

    private String description;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    
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
    
    @Override
    public LocalizedDescription copy() {
        LocalizedDescription copy = new LocalizedDescription();
        Localized.copyTo(this, copy);
        
        copy.setDescription(this.description);
        copy.setShortDescription(this.shortDescription);
        copy.setMediumDescription(this.mediumDescription);
        copy.setLongDescription(this.longDescription);
        
        return copy;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (that == null || !(that instanceof LocalizedDescription)) {
            return false;
        }
        
        LocalizedDescription thatDescription = (LocalizedDescription) that;
        
        return super.equals(that) 
            && Objects.equal(this.description, thatDescription.description)
            && Objects.equal(this.shortDescription, thatDescription.shortDescription)
            && Objects.equal(this.mediumDescription, thatDescription.mediumDescription)
            && Objects.equal(this.longDescription, thatDescription.longDescription);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(),
                description,
                shortDescription,
                mediumDescription,
                longDescription);
    }
}
