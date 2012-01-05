package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.intl.Country;

public class ChannelGroup extends Identified {

    private Publisher publisher;
    private String title;
    private Set<Country> countries;
    
    private Set<Long> channels = ImmutableSet.of();

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

    public Set<Country> getCountries() {
        return countries;
    }

    public void setCountries(Set<Country> countries) {
        this.countries = countries;
    }

    public Set<Long> getChannels() {
        return channels;
    }

    public void setChannels(Iterable<Long> channels) {
        this.channels = ImmutableSet.copyOf(channels);
    }
    
    public void addChannel(Channel channel) {
        addChannel(channel.getId());
    }
    
    public void addChannel(Long id) {
        this.channels = ImmutableSet.<Long>builder().addAll(channels).add(id).build();
    }
}
