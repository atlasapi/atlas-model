package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.entity.simple.ContentIdentifier.BrandIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.EpisodeIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.FilmIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.ItemIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.PersonIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.SeriesIdentifier;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Description supertype for simple model.
 *
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@XmlType(name = "description", namespace = PLAY_SIMPLE_XML.NS)
public abstract class Description extends Aliased {

    private String title;
    private String description;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private Set<LocalizedDescription> descriptions = Sets.newHashSet();
    private Set<LocalizedTitle> localizedTitles = Sets.newHashSet();
    private PublisherDetails publisher;
    private String image;
    private Set<Image> images = Sets.newHashSet();
    private String thumbnail;
    private Set<String> genres = Sets.newHashSet();
    private Set<String> tags = Sets.newHashSet();
    private List<Item> clips = Lists.newArrayList();
    private Set<TopicRef> topics = Sets.newHashSet();
    private List<KeyPhrase> keyPhrases = Lists.newArrayList();
    private List<RelatedLink> relatedLinks = Lists.newArrayList();
    private List<Product> products = Lists.newArrayList();
    private List<ContentGroup> contentGroups = Lists.newArrayList();
    private List<ContentIdentifier> similarContent = Lists.newArrayList();
    private Set<String> sameAs = Sets.newHashSet();
    private Set<SameAs> equivalents = Sets.newHashSet();
    private List<Person> people = Lists.newArrayList();
    private String mediaType;
    private String specialization;
    private boolean scheduleOnly = false;
    private String presentationChannel;
    private Integer year;
    private Set<Language> languages = Sets.newHashSet();
    private Set<Certificate> certificates = Sets.newHashSet();
    private BrandSummary container;
    private Boolean genericDescription;
    private Set<Review> reviews = Sets.newHashSet();
    private AudienceStatistics audienceStatistics;
    private Set<Rating> ratings = Sets.newHashSet();
    private Set<Event> events = Sets.newHashSet();
    private Priority priority;
    private Set<EventRef> eventRefs = Sets.newHashSet();
    private Set<Award> awards = Sets.newHashSet();

    public Description(String uri) {
        super(uri);
    }

    public Description() { /*
         * required for XML/JSON tools
         */    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "genres")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "genre")
    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Iterable<String> genres) {
        this.genres = ImmutableSet.copyOf(genres);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "tags")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "tag")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Iterable<String> tags) {
        this.tags = ImmutableSet.copyOf(tags);
    }
    
    public void setImages(Iterable<Image> images) {
        this.images = ImmutableSet.copyOf(images);
    }

    public PublisherDetails getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return image;
    }
    
    public Set<Image> getImages() {
        return images;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @XmlElementWrapper(name = "clips")
    @XmlElement(name = "clip")
    public List<Item> getClips() {
        return clips;
    }

    @XmlElementWrapper(name = "key_phrases")
    @XmlElement(name = "key_phrase")
    public List<KeyPhrase> getKeyPhrases() {
        return keyPhrases;
    }

    public void setKeyPhrases(Iterable<KeyPhrase> phrases) {
        this.keyPhrases = Lists.newArrayList(phrases);
    }

    @XmlElementWrapper(name = "related_links")
    @XmlElement(name = "related_link")
    public List<RelatedLink> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(Iterable<RelatedLink> links) {
        this.relatedLinks = Lists.newArrayList(links);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
    
    public void setDescriptions(Set<LocalizedDescription> descriptions) {
        this.descriptions = ImmutableSet.copyOf(descriptions);
    }
    
    public void setLocalizedTitles(Set<LocalizedTitle> localizedTitles) {
        this.localizedTitles = ImmutableSet.copyOf(localizedTitles);
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @XmlElementWrapper(name = "descriptions")
    @XmlElement(name = "description")
    public Set<LocalizedDescription> getDescriptions() {
        return descriptions;
    }

    @XmlElementWrapper(name = "localized_titles")
    @XmlElement(name = "localized_title")
    public Set<LocalizedTitle> getLocalizedTitles() {
        return localizedTitles;
    }
    
    public void setReviews(Iterable<Review> reviews) {
        this.reviews = ImmutableSet.copyOf(reviews);
    }
    
    @XmlElementWrapper(name = "reviews")
    @XmlElement(name = "review")
    public Set<Review> getReviews() {
        return reviews;
    }


    public ImmutableSet<String> identifiers() {
        Builder<String> ids = ImmutableSet.builder();
        ids.addAll(aliases);
        ids.add(uri);
        if (curie != null) {
            ids.add(curie);
        }
        return ids.build();
    }

    public void setClips(Iterable<Item> clips) {
        this.clips = Lists.newArrayList(clips);
    }

    public void setSameAs(Iterable<String> sameAs) {
        this.sameAs = Sets.newHashSet(sameAs);
    }
    
    public void setEquivalents(Iterable<SameAs> equivalents) {
        this.equivalents = Sets.newHashSet(equivalents);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "sameAs")
    @XmlElement(name = "uri")
    public Set<String> getSameAs() {
        return sameAs;
    }
    
    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "equivalents")
    @XmlElement(name = "equivalent")
    public Set<SameAs> getEquivalents() {
        return equivalents;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setPresentationChannel(String presentationChannel) {
        this.presentationChannel = presentationChannel;
    }

    public String getPresentationChannel() {
        return this.presentationChannel;
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="people")
    @XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="people")
    public List<Person> getPeople() {
        return people;
    }
    
    public void setPeople(Iterable<Person> people) {
        this.people = Lists.newArrayList(people);
    }
    
    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "similar")
    @XmlElements({
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace = PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace = PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace = PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace = PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace = PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace = PLAY_SIMPLE_XML.NS)
    })
    public List<ContentIdentifier> getSimilarContent() {
        return similarContent;
    }
    
    public void setSimilarContent(Iterable<ContentIdentifier> similarContent) {
        this.similarContent = Lists.newArrayList(similarContent);
    }
    
    public Set<Event> getEvents() {
        return events;
    }
    
    public void setEvents(Iterable<Event> events) {
        this.events = ImmutableSet.copyOf(events);
    }

    protected void copyTo(Description destination) {
        Preconditions.checkNotNull(destination);

        super.copyTo(destination);

        destination.setUri(getUri());
        destination.setTitle(getTitle());
        destination.setDescription(getDescription());

        if (getPublisher() != null) {
            destination.setPublisher(getPublisher().copy());
        }

        destination.setImage(getImage());
        destination.setThumbnail(getThumbnail());
        destination.setGenres(getGenres());
        destination.setTags(getTags());
        destination.setProducts(getProducts());

        destination.setClips(Iterables.transform(getClips(), Item.TO_COPY));

        destination.setSameAs(getSameAs());
        destination.setMediaType(getMediaType());
        destination.setSpecialization(getSpecialization());
        destination.setScheduleOnly(isScheduleOnly());
        destination.setPresentationChannel(getPresentationChannel());
        destination.setYear(getYear());
        destination.setOriginalLanguages(getOriginalLanguages());
        destination.setCertificates(getCertificates());
        
        Set<Person> people = Sets.newHashSet();
        for (Person person: this.people) {
            people.add(person.copy());
        }
        destination.setPeople(people);
        destination.setGenericDescription(getGenericDescription());
        destination.setEvents(Iterables.transform(getEvents(), Event.COPY));
        destination.setPriority(getPriority());
    }

    public boolean isScheduleOnly() {
        return scheduleOnly;
    }

    public void setScheduleOnly(boolean scheduleOnly) {
        this.scheduleOnly = scheduleOnly;
    }

    public static Description copyOf(Description desc) {
        if (desc instanceof Item) {
            return ((Item) desc).copy();
        } else {
            return ((Playlist) desc).copy();
        }
    }

    public abstract Description copy();
    public final static Function<Description, Description> COPY_OF = new Function<Description, Description>() {

        @Override
        public Description apply(Description input) {
            return Description.copyOf(input);
        }
    };
    public final static Function<Description, String> TO_URI = new Function<Description, String>() {

        @Override
        public String apply(Description input) {
            return input.getUri();
        }
    };
    public final static Function<Description, String> TO_ID = new Function<Description, String>() {

        @Override
        public String apply(Description input) {
            return input.getId();
        }
    };

    public void setTopics(Iterable<TopicRef> resolvedTopics) {
        this.topics = ImmutableSet.copyOf(resolvedTopics);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "topics")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "topicref")
    public Set<TopicRef> getTopics() {
        return this.topics;
    }

    public void setContentGroups(Iterable<ContentGroup> contentGroups) {
        this.contentGroups = ImmutableList.copyOf(contentGroups);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "content_groups")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "contentGroupRef")
    public List<ContentGroup> getContentGroups() {
        return this.contentGroups;
    }

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(Iterable<Product> products) {
        this.products = ImmutableList.copyOf(products);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @JsonProperty("languages")
    public void setOriginalLanguages(Iterable<Language> languages) {
        this.languages = ImmutableSet.copyOf(languages);
    }

    public void setCertificates(Iterable<Certificate> certificates) {
        this.certificates = ImmutableSet.copyOf(certificates);
        //        this.certificates = Sets.newHashSet(certificates);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "languages")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "language")
    public Set<Language> getOriginalLanguages() {
        return this.languages;
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "certificates")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "certificate")
    public Set<Certificate> getCertificates() {
        return this.certificates;
    }

    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "container")
    @JsonProperty("container")
    public BrandSummary getBrandSummary() {
    	return container;
    }

    public void setBrandSummary(BrandSummary brand) {
    	this.container = brand;
    }
    
    public void setGenericDescription(Boolean genericDescription) {
        this.genericDescription = genericDescription;
    }
    
    public Boolean getGenericDescription() {
        return genericDescription;
    }

    public AudienceStatistics getAudienceStatistics() {
        return audienceStatistics;
    }

    public void setAudienceStatistics(AudienceStatistics audienceStatistics) {
        this.audienceStatistics = audienceStatistics;
    }
    
    public Set<Rating> getRatings() {
        return ratings;
    }
    
    public void setRatings(Iterable<Rating> ratings) {
        this.ratings = ImmutableSet.copyOf(ratings);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "event_refs")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "eventref")
    public Set<EventRef> getEventRefs() {
        return eventRefs;
    }

    public void setEventRefs(Iterable<EventRef> eventRefs) {
        this.eventRefs = ImmutableSet.copyOf(eventRefs);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "awards")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "award")
    public Set<Award> getAwards() {
        return awards;
    }

    public void setAwards(Set<Award> awards) {
        this.awards = ImmutableSet.copyOf(awards);
    }


}
