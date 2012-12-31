package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.MediaType;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Channel extends Identified {
    
    public static final Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
        
        private Publisher source;
        private String uri;
        private String key;
        private Publisher broadcaster;
        private String title;
        private String image;
        private MediaType mediaType;
        private Boolean highDefinition;
        private Set<Publisher> availableFrom = ImmutableSet.of();
        private Set<Channel> variations = Sets.newHashSet();
        private Channel parent;
        
        public Builder withSource(Publisher source) {
            this.source = source;
            return this;
        };
        
        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }
        
        public Builder withTitle(String title) {
            this.title = title;
            return this;
        };
        
        public Builder withImage(String image) {
            this.image = image;
            return this;
        };
        
        public Builder withMediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        };
        
        @Deprecated
        public Builder withKey(String key) {
            this.key = key;
            return this;
        };
        
        public Builder withHighDefinition(Boolean highDefinition) {
            this.highDefinition = highDefinition;
            return this;
        };
        
        public Builder withBroadcaster(Publisher broadcaster) {
            this.broadcaster = broadcaster;
            return this;
        };
        
        public Builder withAvailableFrom(Iterable<Publisher> availableFrom) {
            this.availableFrom = ImmutableSet.copyOf(availableFrom);
            return this;
        };
        
        public Builder withVariations(Iterable<Channel> variations) {
            this.variations = Sets.newHashSet(variations);
            return this;
        };
        
        public Builder withVariation(Channel variation) {
            this.variations.add(variation);
            return this;
        };
        
        public Builder withParent(Channel parent) {
            this.parent = parent;
            return this;
        }
        
        public Channel build() {
            return new Channel(source, title, key, highDefinition, mediaType, uri, broadcaster, availableFrom, variations, parent, image);
        }
    }
    
    
    private Publisher source;
    private String title;
    private String image;
    private MediaType mediaType;
    private String key;
    private Boolean highDefinition;
    private Publisher broadcaster;
    private Set<Publisher> availableFrom;
    private Set<Channel> variations;
    private Channel parent;
    
    @Deprecated
    public Channel(Publisher publisher, String title, String key, Boolean highDefinition, MediaType mediaType, String uri) {
        this(publisher, title, key, highDefinition, mediaType, uri,null, ImmutableSet.<Publisher>of(), ImmutableSet.<Channel>of(), null, null);
    }
    
    @Deprecated //Required for OldChannel
    protected Channel() { }
    
    private Channel(Publisher publisher, String title, String key, Boolean highDefinition, MediaType mediaType, String uri, Publisher broadcaster, Iterable<Publisher> availableFrom, Iterable<Channel> variations, Channel parent, String image) {
    	    super(uri);
    	    this.source = publisher;
        this.title = title;
        this.parent = parent;
        this.image = image;
        this.key = key;
        this.highDefinition = highDefinition;
        this.mediaType = mediaType;
        this.broadcaster = broadcaster;
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
        this.variations = Sets.newHashSet(variations);
    }
    
    public String uri() {
        return getCanonicalUri();
    }
    
    public String title() {
        return title;
    }
    
    public Boolean highDefinition() {
        return highDefinition;
    }
    
    public MediaType mediaType() {
        return mediaType;
    }
    
    public Publisher source() {
        return source;
    }
    
    public Publisher broadcaster() {
        return broadcaster;
    }
    
    public Set<Publisher> availableFrom() {
        return availableFrom;
    }
    
    public Set<Channel> variations() {
        return variations;
    }
    
    public Channel parent() {
        return parent;
    }
    
    @Deprecated
    public String key() {
        return key;
    }
    
    public String image() {
        return image;
    }
    
    public void setSource(Publisher source) {
        this.source = source;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }
    
    public void setBroadcaster(Publisher broadcaster) {
        this.broadcaster = broadcaster;
    }
    
    public void setAvailableFrom(Iterable<Publisher> availableOn) {
        this.availableFrom = ImmutableSet.copyOf(availableOn);
    }
    
    public void setVariations(Iterable<Channel> variations) {
        this.variations = Sets.newHashSet(variations);
    }
    
    public void addVariation(Channel variation) {
        this.variations.add(variation);
    }
    
    public void setParent(Channel parent) {
        this.parent = parent;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Channel) {
            Channel target = (Channel) obj;
            return getId() != null ? Objects.equal(getId(), target.getId()) 
                                   : Objects.equal(uri(), target.uri());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : uri().hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(getId())
                .addValue(getCanonicalUri())
                .toString();
    }
    
    public static final Function<Channel, String> TO_KEY = new Function<Channel, String>() {
        @Override
        public String apply(Channel input) {
            return input.key();
        }
    };

}
