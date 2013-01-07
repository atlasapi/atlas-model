package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.metabroadcast.common.intl.Country;

public abstract class ChannelGroup extends Identified {

    private Publisher publisher;
    private String title;
    private Set<Country> availableCountries;
    private Set<Long> channels = ImmutableSet.of();
    private Set<ChannelNumbering> channelNumberings = ImmutableSet.of();

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

    @Deprecated
    public Set<Long> getChannels() {
        return channels;
    }

    @Deprecated
    public void setChannels(Iterable<Long> channels) {
        this.channels = ImmutableSet.copyOf(channels);
    }
    
    @Deprecated
    public void addChannel(Long channel) {
        this.channels = ImmutableSet.<Long>builder().addAll(channels).add(channel).build();
    }
    
    public Set<ChannelNumbering> getChannelNumberings() {
        return ImmutableSet.copyOf(Iterables.filter(channelNumberings, new Predicate<ChannelNumbering>() {
            @Override
            public boolean apply(ChannelNumbering input) {
                if (input.getStartDate() != null) {
                    if (input.getEndDate() != null) {
                        return input.getStartDate().isBeforeNow()
                            && input.getEndDate().isAfterNow();
                    } else {
                        return input.getStartDate().isBeforeNow();
                    }
                } else {
                    return true;
                }
            }
        }));
    }
    
    public Set<ChannelNumbering> getAllChannelNumberings() {
        return ImmutableSet.copyOf(channelNumberings);
    }

    public void setChannelNumberings(Iterable<ChannelNumbering> channelNumberings) {
        this.channelNumberings = ImmutableSet.copyOf(channelNumberings);
    }
    
    public void addChannelNumbering(ChannelNumbering channelNumbering) {
        this.channelNumberings = ImmutableSet.<ChannelNumbering>builder().addAll(channelNumberings).add(channelNumbering).build();
    }
}
