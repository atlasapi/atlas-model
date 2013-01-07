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
        private Set<Long> variations = Sets.newHashSet();
        private Long parent;
        private Set<ChannelNumbering> channelNumbers = Sets.newHashSet();
        
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
        
        public Builder withVariationIds(Iterable<Long> variationIds) {
            this.variations = Sets.newHashSet(variationIds);
            return this;
        };
        
        public Builder withVariations(Iterable<Channel> variations) {
            this.variations.clear();
            for (Channel variation : variations) {
                withVariation(variation);
            }
            return this;
        };
        
        public Builder withVariation(Long variationId) {
            this.variations.add(variationId);
            return this;
        };
        
        public Builder withVariation(Channel variation) {
            this.variations.add(variation.getId());
            return this;
        };
        
        public Builder withParent(Channel parent) {
            this.parent = parent.getId();
            return this;
        }
        
        public Builder withChannelNumbers(Iterable<ChannelNumbering> channelNumbers) {
            this.channelNumbers = Sets.newHashSet(channelNumbers);
            return this;
        };
        
        public Builder withChannelNumber(ChannelNumbering channelNumber) {
            this.channelNumbers.add(channelNumber);
            return this;
        };
        
        public Channel build() {
            return new Channel(source, title, key, highDefinition, mediaType, uri, broadcaster, availableFrom, variations, parent, channelNumbers, image);
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
    private Set<Long> variations;
    private Long parent;
    private Set<ChannelNumbering> channelNumbers;
    
    @Deprecated
    public Channel(Publisher publisher, String title, String key, Boolean highDefinition, MediaType mediaType, String uri) {
        this(publisher, title, key, highDefinition, mediaType, uri, null, ImmutableSet.<Publisher>of(), ImmutableSet.<Long>of(), null, ImmutableSet.<ChannelNumbering>of(), null);
    }
    
    @Deprecated //Required for OldChannel
    protected Channel() { }
    
    private Channel(Publisher publisher, String title, String key, Boolean highDefinition, MediaType mediaType, String uri, Publisher broadcaster, Iterable<Publisher> availableFrom, Iterable<Long> variations, Long parent, Iterable<ChannelNumbering> channelNumbers, String image) {
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
        this.channelNumbers = Sets.newHashSet(channelNumbers);
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
    
    public Set<Long> variations() {
        return ImmutableSet.copyOf(variations);
    }
    
    public Long parent() {
        return parent;
    }
    
    public Set<ChannelNumbering> channelNumbers() {
        return ImmutableSet.copyOf(channelNumbers);
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
        this.variations.clear();
        for (Channel variation : variations) {
            addVariation(variation);
        }
    }
    
    public void setVariationIds(Iterable<Long> variationIds) {
        this.variations = Sets.newHashSet(variationIds);
    }
    
    public void addVariation(Channel variation) {
        this.variations.add(variation.getId());
    }
    
    public void addVariation(Long variationId) {
        this.variations.add(variationId);
    }
    
    public void setParent(Channel parent) {
        this.parent = parent.getId();
    }
    
    public void setParent(Long parentId) {
        this.parent = parentId;
    }
    
    public void setChannelNumbers(Iterable<ChannelNumbering> channelNumbers) {
        this.channelNumbers = Sets.newHashSet(channelNumbers);
    }
    
    public void addChannelNumber(ChannelNumbering channelNumber) {
        this.channelNumbers.add(channelNumber);
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
