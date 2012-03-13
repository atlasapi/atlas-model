package org.atlasapi.media.entity;

import com.google.common.base.Objects;

/**
 * A Description of something
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public class Description {
    
    public static final Description EMPTY = new Description("","","","","","");
    
    public static class Builder {

        private String title;

        private String shortSynopsis;
        private String mediumSynopsis;
        private String longSynopsis;

        private String image;
        private String thumbnail;
        
        private Builder() {}
        
        private Builder(String t, String ss, String ms, String ls, String img, String thmb) {
            this.title = t;
            this.shortSynopsis = ss;
            this.longSynopsis = ls;
            this.image = img;
            this.thumbnail = thmb;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withShortSynopsis(String shortSynopsis) {
            this.shortSynopsis = shortSynopsis;
            return this;
        }

        public Builder withMediumSynopsis(String mediumSynopsis) {
            this.mediumSynopsis = mediumSynopsis;
            return this;
        }

        public Builder withLongSynopsis(String longSynopsis) {
            this.longSynopsis = longSynopsis;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
            return this;
        }

        public Description build() {
            return new Description(title, shortSynopsis, mediumSynopsis, longSynopsis, image, thumbnail);
        }
    }

    public static final Builder description() {
        return new Builder();
    }
    
    public Description(String title, String shortSynopsis, String mediumSynopsis, String longSynopsis, String image, String thumbnail) {
        this.title = title;
        this.shortSynopsis = shortSynopsis;
        this.mediumSynopsis = mediumSynopsis;
        this.longSynopsis = longSynopsis;
        this.image = image;
        this.thumbnail = thumbnail;
        this.hash = Objects.hashCode(title,shortSynopsis,mediumSynopsis,longSynopsis,image,thumbnail);
    }

    protected Description() {
    }
    
    private String title;
    
    private String shortSynopsis;
    private String mediumSynopsis;
    private String longSynopsis;

    private String image;
    private String thumbnail;
    
    private transient int hash;
    
    public String getTitle() {
        return this.title;
    }

    public String getShortSynopsis() {
        return this.shortSynopsis;
    }

    public String getMediumSynopsis() {
        return this.mediumSynopsis;
    }

    public String getLongSynopsis() {
        return this.longSynopsis;
    }

    public String getImage() {
        return this.image;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }
    
    public Builder copy() {
        return new Builder(title, shortSynopsis, mediumSynopsis, longSynopsis, image, thumbnail);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Description) {
            Description other = (Description) that;
            return other.hash == this.hash;
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return hash;
    }
    
    @Override
    public String toString() {
        return String.format("%s Description", title);
    }
}
