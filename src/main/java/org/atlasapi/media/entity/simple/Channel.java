package org.atlasapi.media.entity.simple;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class Channel extends Aliased {

    private PublisherDetails source;
    private String title;
    private String mediaType;
    private Set<ChannelGroup> channelGroups;
    private PublisherDetails broadcaster;
    private Set<PublisherDetails> availableFrom;

    public void setSourceDetails(PublisherDetails publisherDetails) {
        this.source = publisherDetails;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public PublisherDetails getSourceDetails() {
        return this.source;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setChannelGroups(Iterable<ChannelGroup> groups) {
        this.channelGroups = ImmutableSet.copyOf(groups);
    }

    public Set<ChannelGroup> getChannelGroups() {
        return this.channelGroups;
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
}
