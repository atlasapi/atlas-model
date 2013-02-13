package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.MediaType;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

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
        private Set<TemporalString> images = Sets.newHashSet();
        private Set<TemporalString> titles = Sets.newHashSet();
        private MediaType mediaType;
        private Boolean regional;
        private Boolean highDefinition;
        private Duration timeshift;
        private Set<Publisher> availableFrom = ImmutableSet.of();
        private Set<Long> variations = Sets.newHashSet();
        private Long parent;
        private Set<ChannelNumbering> channelNumbers = Sets.newHashSet();
        private LocalDate startDate;
        private LocalDate endDate;
        
        public Builder withSource(Publisher source) {
            this.source = source;
            return this;
        };
        
        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }
        
        public Builder withTitle(String title) {
            return withTitle(title, null, null);
        };
        
        public Builder withTitle(String title, LocalDate startDate) {
            return withTitle(title, startDate, null);
        };
        
        public Builder withTitle(String title, LocalDate startDate, LocalDate endDate) {
            this.titles.add(new TemporalString(title, startDate, endDate));
            return this;
        };
        
        public Builder withImage(String image) {
            return withImage(image, null, null);
        };
        
        public Builder withImage(String image, LocalDate startDate) {
            return withImage(image, startDate, endDate);
        };
        
        public Builder withImage(String image, LocalDate startDate, LocalDate endDate) {
            this.images.add(new TemporalString(image, startDate, endDate));
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
        
        public Builder withRegional(Boolean regional) {
            this.regional = regional;
            return this;
        };
        
        public Builder withTimeshift(Duration timeshift) {
            this.timeshift = timeshift;
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
        
        public Builder withParent(Long parentId) {
            this.parent = parentId;
            return this;
        }
        
        public Builder withChannelNumbers(Iterable<ChannelNumbering> channelNumbers) {
            this.channelNumbers = Sets.newHashSet(channelNumbers);
            return this;
        }
        
        public Builder withChannelNumber(ChannelNumbering channelNumber) {
            this.channelNumbers.add(channelNumber);
            return this;
        }
        
        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }
        
        public Builder withEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }
        
        public Channel build() {
            return new Channel(source, titles, images, key, highDefinition, regional, timeshift, mediaType, uri, broadcaster, availableFrom, variations, parent, channelNumbers, startDate, endDate);
        }
    }
    
    
    private Publisher source;
    private Set<TemporalString> titles = Sets.newHashSet();
    private Set<TemporalString> images = Sets.newHashSet();
    private MediaType mediaType;
    private String key;
    private Boolean highDefinition;
    private Boolean regional;
    private Duration timeshift;
    private Publisher broadcaster;
    private Set<Publisher> availableFrom;
    private Set<Long> variations = Sets.newHashSet();
    private Long parent;
    private Set<ChannelNumbering> channelNumbers = Sets.newHashSet();
    private LocalDate startDate;
    private LocalDate endDate;
    
    @Deprecated
    public Channel(Publisher publisher, String title, String key, Boolean highDefinition, MediaType mediaType, String uri) {
        this(publisher, Sets.newHashSet(new TemporalString(title, null, null)), ImmutableSet.<TemporalString>of(), key, highDefinition, null, null, mediaType, uri, null, ImmutableSet.<Publisher>of(), ImmutableSet.<Long>of(), null, ImmutableSet.<ChannelNumbering>of(), null, null);
    }
    
    @Deprecated //Required for OldChannel
    protected Channel() { }
    
    private Channel(Publisher publisher, Set<TemporalString> titles, Set<TemporalString> images, String key, Boolean highDefinition, Boolean regional, Duration timeshift, MediaType mediaType, String uri, Publisher broadcaster, Iterable<Publisher> availableFrom, Iterable<Long> variations, Long parent, Iterable<ChannelNumbering> channelNumbers, LocalDate startDate, LocalDate endDate) {
        super(uri);
        this.source = publisher;
        this.regional = regional;
        this.timeshift = timeshift;
        this.titles = Sets.newHashSet(titles);
        this.images = Sets.newCopyOnWriteArraySet(images);
        this.parent = parent;
        this.key = key;
        this.highDefinition = highDefinition;
        this.mediaType = mediaType;
        this.broadcaster = broadcaster;
        this.startDate = startDate;
        this.endDate = endDate;
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
        this.variations = Sets.newHashSet(variations);
        this.channelNumbers = Sets.newHashSet(channelNumbers);
    }
    
    public String uri() {
        return getCanonicalUri();
    }
    
    public String title() {
        return TemporalString.valueForDate(titles, new LocalDate());
    }
    
    public String titleForDate(LocalDate date) {
        return TemporalString.valueForDate(titles, date);
    }
    
    public Iterable<TemporalString> allTitles() {
        return ImmutableSet.copyOf(titles);
    }
    
    public Boolean highDefinition() {
        return highDefinition;
    }
    
    public Boolean regional() {
        return regional;
    }
    
    public Duration timeshift() {
        return timeshift;
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
        return variations;
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
        return TemporalString.valueForDate(images, new LocalDate());
    }
    
    public String imageForDate(LocalDate date) {
        return TemporalString.valueForDate(images, date);
    }
    
    public Iterable<TemporalString> allImages() {
        return ImmutableSet.copyOf(images);
    }
    
    public LocalDate startDate() {
        return startDate;
    }
    
    public LocalDate endDate() {
        return endDate;
    }
    
    public void setSource(Publisher source) {
        this.source = source;
    }
    
    public void addTitle(String title) {
        addTitle(title, null);
    }
    
    public void addTitle(String title, LocalDate startDate) {
        addTitle(title, startDate, null);
    }
    
    public void addTitle(String title, LocalDate startDate, LocalDate endDate) {
        this.titles.add(new TemporalString(title, startDate, endDate));
    }
    
    public void addTitle(TemporalString title) {
        this.titles.add(title);
    }
    
    public void setTitles(Iterable<TemporalString> titles) {
        this.titles = Sets.newHashSet(titles);
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

    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public void setTimeshift(Duration timeshift) {
        this.timeshift = timeshift;
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    
    public void addChannelNumber(ChannelGroup channelGroup, String channelNumber, LocalDate startDate, LocalDate endDate) {
        ChannelNumbering channelNumbering = ChannelNumbering.builder()
            .withChannelGroup(channelGroup)
            .withChannelNumber(channelNumber)
            .withStartDate(startDate)
            .withEndDate(endDate)
            .build();
        this.channelNumbers.add(channelNumbering);
    };
    
    public void addImage(String image) {
        addImage(image, null, null);
    }
    
    public void addImage(String image, LocalDate startDate) {
        addImage(image, startDate, null);
    }
    
    public void addImage(String image, LocalDate startDate, LocalDate endDate) {
        this.images.add(new TemporalString(image, startDate, endDate));
    }
    
    public void addImage(TemporalString image) {
        this.images.add(image);
    }
    
    public void setImages(Iterable<TemporalString> images) {
        this.images = Sets.newHashSet(images);
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
