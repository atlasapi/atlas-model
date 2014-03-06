package org.atlasapi.media.entity;

import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Objects;

public abstract class Localized {
    
    private final static Function<Localized, String> TO_LANGUAGE_TAG = new Function<Localized, String>() {
        @Override
        @Nullable
        public String apply(Localized localized) {
            return localized.locale != null ? localized.locale.toLanguageTag() : null;
        }
    };
    
    private Locale locale;
    
    public void setLocale(Locale locale) {
        this.locale = locale;
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getLanguageTag() {
        return TO_LANGUAGE_TAG.apply(this);
    }
    
    protected static void copyTo(Localized from, Localized to) {
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
