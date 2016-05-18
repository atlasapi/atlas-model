package org.atlasapi.media.entity.simple;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.channel.ChannelType;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="Channel", namespace=PLAY_SIMPLE_XML.NS)
public class Channel extends Aliased {

    private static final Ordering<HistoricalChannelEntry> HISTORY_ORDERING = Ordering.natural();
    private static final Ordering<ChannelNumbering> NUMBERING_ORDERING = new ChannelNumberingOrdering();

    private PublisherDetails publisher;
    private String title;
    private String image;
    private Set<Image> images;
    private String mediaType;
    private Boolean highDefinition;        
    private Boolean regional;
    private Boolean adult;
    private Long timeshift;
    private List<ChannelNumbering> channelGroups;
    private List<ChannelGroupSummary> groups;
    private PublisherDetails broadcaster;
    private Set<PublisherDetails> availableFrom;
    private Channel parent;
    private Set<Channel> variations;
    private List<RelatedLink> relatedLinks = Lists.newArrayList();
    private List<HistoricalChannelEntry> history;
    private Date startDate;
    private Date endDate;
    private Set<String> genres = Sets.newHashSet();
    private Date advertisedFrom;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private String region;
    private String channelType;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMediumDescription() {
        return mediumDescription;
    }

    public void setMediumDescription(String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public Date getAdvertisedFrom() { return advertisedFrom; }

    public void setAdvertisedFrom(Date advertisedFrom) { this.advertisedFrom = advertisedFrom;}

    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisher = publisherDetails;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setImages(Iterable<Image> images) {
        this.images = ImmutableSet.copyOf(images);
    }
    
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }
    
    public void setRegional(Boolean regional) {
        this.regional = regional;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }
    
    public void setTimeshift(Long timeshift) {
        this.timeshift = timeshift;
    }

    @XmlElement(name = "publisher")
    public PublisherDetails getPublisherDetails() {
        return this.publisher;
    }

    public String getTitle() {
        return this.title;
    }

    public String getImage() {
        return this.image;
    }

    public Set<Image> getImages() {
        return this.images;
    }
    
    public String getMediaType() {
        return this.mediaType;
    }
    
    public Boolean getHighDefinition() {
        return highDefinition;
    }
    
    public Boolean getRegional() {
        return regional;
    }
    
    public Boolean getAdult() {
        return adult;
    }
    
    public Long getTimeshift() {
        return timeshift;
    }

    public void setChannelGroups(List<ChannelNumbering> channelNumbering) {
        this.channelGroups = NUMBERING_ORDERING.immutableSortedCopy(channelNumbering);
    }
    
    public void setChannelGroups(Iterable<ChannelNumbering> channelNumbering) {
        this.channelGroups = NUMBERING_ORDERING.immutableSortedCopy(channelNumbering);
    }
    
    public void setGroups(Iterable<ChannelGroupSummary> aliases) {
        this.groups = ImmutableList.copyOf(aliases);
    }
    
    @XmlElementWrapper(name = "groups")
    @XmlElement(name = "group")
    public List<ChannelGroupSummary> getGroups() {
        return groups;
    }
    
    public void setRelatedLinks(Iterable<RelatedLink> relatedLinks) {
        this.relatedLinks = ImmutableList.copyOf(relatedLinks);
    }
    
    public List<RelatedLink> getRelatedLinks() {
        return relatedLinks;
    }

    @XmlElementWrapper(name = "channelGroups")
    @XmlElement(name = "channelNumbering")
    public List<ChannelNumbering> getChannelGroups() {
        return channelGroups;
    }

    public PublisherDetails getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(PublisherDetails broadcaster) {
        this.broadcaster = broadcaster;
    }
    
    public void setAvailableFrom(Set<PublisherDetails> availableFrom) {
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
    }
    
    public void setAvailableFrom(Iterable<PublisherDetails> availableFrom) {
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
    }
    
    @XmlElementWrapper(name = "availableFrom")
    @XmlElement(name = "publisher")
    public Set<PublisherDetails> getAvailableFrom() {
        return availableFrom;
    }

    public Channel getParent() {
        return parent;
    }

    public void setParent(Channel parent) {
        this.parent = parent;
    }

    @XmlElementWrapper(name = "variations")
    @XmlElement(name = "variation")
    public Set<Channel> getVariations() {
        return variations;
    }

    public void setVariations(Set<Channel> variations) {
        this.variations = ImmutableSet.copyOf(variations);
    }

    public void setVariations(Iterable<Channel> variations) {
        this.variations = ImmutableSet.copyOf(variations);
    }

    @XmlElementWrapper(name = "history")
    @XmlElement(name = "historyEntry")
    public List<HistoricalChannelEntry> getHistory() {
        return history;
    }

    public void setHistory(Iterable<HistoricalChannelEntry> history) {
        this.history = HISTORY_ORDERING.immutableSortedCopy(history);
    }

    @XmlElement(name = "startDate")    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDateTimeAtStartOfDay(DateTimeZone.UTC).toDate();
        }
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (endDate != null) {
            this.startDate = endDate.toDate();
        }
    }
    
    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Iterable<String> genres) {
        this.genres = Sets.newHashSet(genres);
    }
}
