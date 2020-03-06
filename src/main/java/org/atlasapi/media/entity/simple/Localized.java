package org.atlasapi.media.entity.simple;

import javax.annotation.Nullable;

import com.google.common.base.Objects;

public class Localized {

    private String language;
    private String region;  // 2-character country code (ISO 3166 alpha-2)

    public Localized() {
    }

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

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that == null || !(that instanceof Localized)) {
            return false;
        }

        Localized thatLocalized = (Localized) that;

        return Objects.equal(this.language, thatLocalized.language) &&
                Objects.equal(this.region, thatLocalized.language)
                ;
    }
}
