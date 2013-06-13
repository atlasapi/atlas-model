package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Ordering;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="channel_group", namespace=PLAY_SIMPLE_XML.NS)
public class ChannelGroup extends Aliased {

    private static final Ordering<HistoricalChannelGroupEntry> HISTORY_ORDERING = Ordering.natural();
    private static final Ordering<ChannelNumbering> NUMBERING_ORDERING = new ChannelNumberingOrdering();
    
    private PublisherDetails publisher;
    private String title;
    private Set<String> availableCountries;
    private List<ChannelNumbering> channels;
    private Set<ChannelGroup> regions;
    private ChannelGroup platform;
    private List<HistoricalChannelGroupEntry> history;
    
    public PublisherDetails getPublisherDetails() {
        return this.publisher;
    }
    
    @XmlElement(name = "publisher")
    public void setPublisherDetails(PublisherDetails publisher) {
        this.publisher = publisher;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElementWrapper(name = "availableCountries")
    @XmlElement(name = "availableCountry")
    public Set<String> getAvailableCountries() {
        return this.availableCountries;
    }
    
    public void setAvailableCountries(Set<String> countries) {
        this.availableCountries = countries;
    }
    
    public void setChannels(Iterable<ChannelNumbering> channelNumbering) {
        this.channels = NUMBERING_ORDERING.immutableSortedCopy(channelNumbering);
    }

    @XmlElementWrapper(name = "channels")
    @XmlElement(name = "channelNumbering")
    public List<ChannelNumbering> getChannels() {
        return channels;
    }
    
    @XmlElementWrapper(name = "regions")
    @XmlElement(name = "region")
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
    
    @XmlElementWrapper(name = "history")
    @XmlElement(name = "historyEntry")
    public List<HistoricalChannelGroupEntry> getHistory() {
        return history;
    }
    
    public void setHistory(Iterable<HistoricalChannelGroupEntry> history) {
        this.history = HISTORY_ORDERING.immutableSortedCopy(history);
    }
}
