package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.intl.Country;

public class ChannelGroup extends Identified {

    private Publisher publisher;
    private String title;
    private Set<Country> availableCountries;
    private Set<ChannelNumbering> channels = ImmutableSet.of();

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Country> getAvailableCountries() {
        return availableCountries;
    }

    public void setAvailableCountries(Set<Country> availableCountries) {
        this.availableCountries = availableCountries;
    }

    public Set<ChannelNumbering> getChannels() {
        return channels;
    }

    public void setChannels(Iterable<ChannelNumbering> channels) {
        this.channels = ImmutableSet.copyOf(channels);
    }
    
    public void addChannel(ChannelNumbering channel) {
        this.channels = ImmutableSet.<ChannelNumbering>builder().addAll(channels).add(channel).build();
    }
}
