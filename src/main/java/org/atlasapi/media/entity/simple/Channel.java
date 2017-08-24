package org.atlasapi.media.entity.simple;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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
    private Boolean isTimeshifted;
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
    private Date advertisedTo;
    private Set<ChannelRef> sameAs;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private String region;
    private String channelType;
    private Set<String> targetRegions = Sets.newHashSet();
    private Boolean interactive;

    @Nullable
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(@Nullable String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Nullable
    public String getMediumDescription() {
        return mediumDescription;
    }

    public void setMediumDescription(@Nullable String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }

    @Nullable
    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(@Nullable String longDescription) {
        this.longDescription = longDescription;
    }

    @Nullable
    public String getRegion() {
        return region;
    }

    public void setRegion(@Nullable String region) {
        this.region = region;
    }

    @Nullable
    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(@Nullable String channelType) {
        this.channelType = channelType;
    }

    @Nullable
    public Boolean getInteractive() {
        return interactive;
    }

    public void setInteractive(@Nullable Boolean interactive) {
        this.interactive = interactive;
    }

    @Nullable
    public Date getAdvertisedFrom() { return advertisedFrom; }

    public void setAdvertisedFrom(@Nullable Date advertisedFrom) { this.advertisedFrom = advertisedFrom;}

    @Nullable
    public Date getAdvertisedTo() {
        return advertisedTo;
    }

    public void setAdvertisedTo(@Nullable Date advertisedTo) {
        this.advertisedTo = advertisedTo;
    }

    public Set<ChannelRef> getSameAs() {
        return sameAs;
    }

    public void setSameAs(Set<ChannelRef> sameAs) {
        this.sameAs = sameAs;
    }

    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisher = publisherDetails;
    }

    public void setTitle(@Nullable String title) {
        this.title = title;
    }

    public void setImage(@Nullable String image) {
        this.image = image;
    }

    public void setImages(@Nullable Iterable<Image> images) {
        this.images = ImmutableSet.copyOf(images);
    }
    
    public void setMediaType(@Nullable String mediaType) {
        this.mediaType = mediaType;
    }
    
    public void setHighDefinition(@Nullable Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }

    public void setIsTimeshifted(@Nullable Boolean isTimeshifted) {
        this.isTimeshifted = isTimeshifted;
    }
    
    public void setRegional(@Nullable Boolean regional) {
        this.regional = regional;
    }

    public void setAdult(@Nullable Boolean adult) {
        this.adult = adult;
    }
    
    public void setTimeshift(@Nullable Long timeshift) {
        this.timeshift = timeshift;
    }

    @XmlElement(name = "publisher")
    public PublisherDetails getPublisherDetails() {
        return this.publisher;
    }

    @Nullable
    public String getTitle() {
        return this.title;
    }

    @Nullable
    public String getImage() {
        return this.image;
    }

    @Nullable
    public Set<Image> getImages() {
        return this.images;
    }

    @Nullable
    public String getMediaType() {
        return this.mediaType;
    }

    @Nullable
    public Boolean getHighDefinition() {
        return highDefinition;
    }

    @Nullable
    public Boolean isTimeshifted() {
        return isTimeshifted;
    }

    @Nullable
    public Boolean getRegional() {
        return regional;
    }

    @Nullable
    public Boolean getAdult() {
        return adult;
    }

    @Nullable
    public Long getTimeshift() {
        return timeshift;
    }

    public void setChannelGroups(Iterable<ChannelNumbering> channelNumbering) {
        this.channelGroups = NUMBERING_ORDERING.immutableSortedCopy(channelNumbering);
    }
    
    public void setGroups(Iterable<ChannelGroupSummary> aliases) {
        this.groups = ImmutableList.copyOf(aliases);
    }

    @Nullable
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

    @Nullable
    @XmlElementWrapper(name = "channelGroups")
    @XmlElement(name = "channelNumbering")
    public List<ChannelNumbering> getChannelGroups() {
        return channelGroups;
    }

    @Nullable
    public PublisherDetails getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(PublisherDetails broadcaster) {
        this.broadcaster = broadcaster;
    }

    public void setAvailableFrom(Iterable<PublisherDetails> availableFrom) {
        this.availableFrom = ImmutableSet.copyOf(availableFrom);
    }

    @Nullable
    @XmlElementWrapper(name = "availableFrom")
    @XmlElement(name = "publisher")
    public Set<PublisherDetails> getAvailableFrom() {
        return availableFrom;
    }

    @Nullable
    public Channel getParent() {
        return parent;
    }

    public void setParent(@Nullable Channel parent) {
        this.parent = parent;
    }

    @Nullable
    @XmlElementWrapper(name = "variations")
    @XmlElement(name = "variation")
    public Set<Channel> getVariations() {
        return variations;
    }

    public void setVariations(Iterable<Channel> variations) {
        this.variations = ImmutableSet.copyOf(variations);
    }

    @Nullable
    @XmlElementWrapper(name = "history")
    @XmlElement(name = "historyEntry")
    public List<HistoricalChannelEntry> getHistory() {
        return history;
    }

    public void setHistory(@Nullable Iterable<HistoricalChannelEntry> history) {
        if (history != null) {
            this.history = HISTORY_ORDERING.immutableSortedCopy(history);
        }
    }

    @Nullable
    @XmlElement(name = "startDate")    
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@Nullable LocalDate startDate) {
        if (startDate != null) {
            this.startDate = startDate.toDateTimeAtStartOfDay(DateTimeZone.UTC).toDate();
        }
    }

    @Nullable
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@Nullable LocalDate endDate) {
        if (endDate != null) {
            this.startDate = endDate.toDate();
        }
    }

    public Set<String> getTargetRegions() {
        return targetRegions;
    }

    public void setTargetRegions(Set<String> targetRegions) {
        this.targetRegions = targetRegions;
    }

    @Nullable
    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Iterable<String> genres) {
        this.genres = Sets.newHashSet(genres);
    }
}
