package org.atlasapi.media.entity.simple;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class Channel extends Aliased {

    private PublisherDetails publisherDetails;
    private String title;
    private String mediaType;
    private Set<ChannelGroup> groups;

    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisherDetails = publisherDetails;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public PublisherDetails getPublisherDetails() {
        return this.publisherDetails;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setGroups(Iterable<ChannelGroup> groups) {
        this.groups = ImmutableSet.copyOf(groups);
    }

    public Set<ChannelGroup> getGroups() {
        return this.groups;
    }
}
