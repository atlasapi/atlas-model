package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

public class ChannelGroup extends Aliased {

    private static final Ordering<ChannelNumbering> ORDERING = Ordering.natural();
    
    private PublisherDetails publisher;
    private String title;
    private Set<String> availableCountries;
    private List<ChannelNumbering> channels;
    private Set<ChannelGroup> regions;
    private ChannelGroup platform;
    private History history;
    
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
    public Set<String> getAvailableCountries() {
        return this.availableCountries;
    }
    public void setAvailableCountries(Set<String> countries) {
        this.availableCountries = countries;
    }
    public void setChannels(Iterable<ChannelNumbering> channelNumbering) {
        this.channels = ORDERING.immutableSortedCopy(channelNumbering);
    }
    public List<ChannelNumbering> getChannels() {
        return channels;
    }
    public Set<ChannelGroup> getRegions() {
        return regions;
    }
    public void setRegions(Iterable<ChannelGroup> regions) {
        this.regions = ImmutableSet.copyOf(regions);
    }
    public ChannelGroup getPlatform() {
        return platform;
    }
    public void setPlatform(ChannelGroup platform) {
        this.platform = platform;
    }
    public History getHistory() {
        return history;
    }
    public void setHistory(History history) {
        this.history = history;
    }
}
