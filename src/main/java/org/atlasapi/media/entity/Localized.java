package org.atlasapi.media.entity;

import java.util.Locale;

import com.google.common.base.Objects;


public abstract class Localized {
    
    private Locale locale;
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getLanguageTag() {
        return locale == null ? null : locale.toLanguageTag();
    }
    
    public static void copyTo(Localized from, Localized to) {
        to.locale = from.locale;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (that == null || !(that instanceof Localized)) {
            return false;
        }
        
        if (!super.equals(that)) {
            return false;
        }
        
        Localized thatLocalized = (Localized) that;
        
        return Objects.equal(this.locale, thatLocalized.locale);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale);
    }
    
    public abstract Localized copy();
    
}
