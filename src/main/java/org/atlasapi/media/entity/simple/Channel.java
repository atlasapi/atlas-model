package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public class Channel extends Aliased {
    
    private static final Ordering<ChannelNumbering> ORDERING = Ordering.natural();

    private PublisherDetails publisher;
    private String title;
    private String image;
    private String mediaType;
    private Boolean highDefinition;
    private List<ChannelNumbering> channelGroups;
    private PublisherDetails broadcaster;
    private Set<PublisherDetails> availableFrom;
    private Channel parent;
    private Set<Channel> variations;
    private History history;

    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisher = publisherDetails;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }

    public PublisherDetails getPublisherDetails() {
        return this.publisher;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }
    
    public String getMediaType() {
        return this.mediaType;
    }
    
    public Boolean getHighDefinition() {
        return highDefinition;
    }

    public void setChannelGroups(Iterable<ChannelNumbering> channelNumbering) {
        this.channelGroups = ORDERING.immutableSortedCopy(channelNumbering);
    }

    public List<ChannelNumbering> getChannelGroups() {
        return channelGroups;
    }

    public PublisherDetails getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(PublisherDetails broadcaster) {
        this.broadcaster = broadcaster;
    }
    
    public void setAvailableFrom(Iterable<PublisherDetails> availableFrom) {
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
    }
    
    public Set<PublisherDetails> getAvailableFrom() {
        return availableFrom;
    }

    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }

    public Set<Channel> getVariations() {
        return variations;
    }

    public void setVariations(Iterable<Channel> variations) {
        this.variations = ImmutableSet.copyOf(variations);
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }
}
