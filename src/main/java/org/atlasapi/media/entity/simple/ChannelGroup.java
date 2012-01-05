package org.atlasapi.media.entity.simple;

import java.util.Set;

public class ChannelGroup extends Aliased {

    private PublisherDetails publisher;
    private String title;
    private Set<String> countries;
    private Set<Channel> channels;
    
    public PublisherDetails getPublisherDetails() {
        return this.publisher;
    }
    public void setPublisherDetails(PublisherDetails publisher) {
        this.publisher = publisher;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Set<String> getCountries() {
        return this.countries;
    }
    public void setCountries(Set<String> countries) {
        this.countries = countries;
    }
    public Set<Channel> getChannels() {
        return this.channels;
    }
    public void setChannels(Set<Channel> channels) {
        this.channels = channels;
    }
    
}
