package org.atlasapi.media.entity.simple;


public class Localized {

    private String language;
    private String region;  // 2-character country code (ISO 3166 alpha-2)
    
    public Localized() {}
    
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
