package org.atlasapi.media.entity.simple;

import javax.annotation.Nullable;

public class Localized {

    private String language;
    private String region;  // 2-character country code (ISO 3166 alpha-2)
    
    public Localized() {}

    @Nullable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Nullable
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
