package org.atlasapi.media.entity;

import java.util.Locale;

import com.google.common.base.Objects;


/**
 * A Localised description of a piece of content
 */
public class LocalisedDescription {

    private Locale locale;
    private String title;
    private String description;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    
    public Locale getLocale() {
        return locale;
    }
    
    public void setLocale(Locale locale) {
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
    
    public LocalisedDescription copy(LocalisedDescription original) {
        LocalisedDescription copy = new LocalisedDescription();
        
        copy.setLocale(original.locale);
        copy.setTitle(original.title);
        copy.setDescription(original.description);
        copy.setShortDescription(original.shortDescription);
        copy.setMediumDescription(original.mediumDescription);
        copy.setLongDescription(original.longDescription);
        
        return copy;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (that == null || !(that instanceof LocalisedDescription)) {
            return false;
        }
        
        LocalisedDescription thatDescription = (LocalisedDescription) that;
        
        return Objects.equal(this.locale, thatDescription.locale)
            && Objects.equal(this.title, thatDescription.title)
            && Objects.equal(this.description, thatDescription.description)
            && Objects.equal(this.shortDescription, thatDescription.shortDescription)
            && Objects.equal(this.mediumDescription, thatDescription.mediumDescription)
            && Objects.equal(this.longDescription, thatDescription.longDescription);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale,
                title,
                description,
                shortDescription,
                mediumDescription,
                longDescription);
    }
}
