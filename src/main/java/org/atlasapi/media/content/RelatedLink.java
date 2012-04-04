package org.atlasapi.media.content;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.common.Description;

import com.google.common.base.Objects;

/**
 * Represents a link to a resource in some way relevant to the Content to which
 * it's attached.
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 */
public class RelatedLink {
    
    public static Builder facebookLink(String url) {
        return relatedLink(LinkType.FACEBOOK, url);
    }
    
    public static Builder twitterLink(String url) {
        return relatedLink(LinkType.TWITTER, url);
    }
    
    public static Builder flickLink(String url) {
        return relatedLink(LinkType.FLICKR, url);
    }
    
    public static Builder unknownTypeLink(String url) {
        return relatedLink(LinkType.UNKNOWN, url);
    }
    
    public static Builder relatedLink(LinkType type, String url) {
        return new Builder(checkNotNull(type), checkNotNull(url));
    }
    
    public static class Builder {
        
        private final LinkType type;
        private final String url;
        
        private String sourceId;
        private String shortName;
        private Description description;

        public Builder(LinkType type, String url) {
            this.type = type;
            this.url = url;
        }

        /**
         * The id of the linked resource in the external system.
         * @param sourceId - the id of the linked resource
         * @return this builder
         */
        public Builder withSourceId(String sourceId) {
            this.sourceId = sourceId;
            return this;
        }

        /**
         * The short name of the resource in the external system.
         * @param shortName - the short name of the linked resource
         * @return this builder
         */
        public Builder withShortName(String shortName) {
            this.shortName = shortName;
            return this;
        }
        
        public Builder withDescription(Description description) {
            this.description = description;
            return this;
        }
        
        public Builder withDescription(Description.Builder description) {
            this.description = description.build();
            return this;
        }

        
        public RelatedLink build() {
            return new RelatedLink(this);
        }
        
    }
    
    public enum LinkType {
        FACEBOOK,
        TWITTER,
        FLICKR,
        UNKNOWN
    }
    
    private final String url;
    private final LinkType type;
    
    private String sourceId;
    private String shortName;
    private Description description;
    
    private RelatedLink(Builder b) {
        this.type = b.type;
        this.url = b.url;
        this.sourceId = b.sourceId;
        this.shortName = b.shortName;
        this.description = b.description;
    }

    public String url() {
        return this.url;
    }

    public LinkType type() {
        return this.type;
    }
    
    public String sourceId() {
        return this.sourceId;
    }

    public String shortName() {
        return this.shortName;
    }

    public Description description() {
        return this.description;
    }
    
    public Builder copy() {
        return relatedLink(type(), url())
                .withSourceId(sourceId())
                .withShortName(shortName())
                .withDescription(description);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof RelatedLink) {
            RelatedLink other = (RelatedLink) that;
            return type == other.type && url.equals(url);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(type, url);
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s", type, url);
    }
}
