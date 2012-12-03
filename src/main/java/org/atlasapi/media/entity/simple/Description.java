package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

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
    private PublisherDetails source;
    private String image;
    private String thumbnail;
    private Set<String> genres = Sets.newHashSet();
    private Set<String> tags = Sets.newHashSet();
    private List<Item> clips = Lists.newArrayList();
    private Set<TopicRef> topics = Sets.newHashSet();
    private List<KeyPhrase> keyPhrases = Lists.newArrayList();
    private List<RelatedLink> relatedLinks = Lists.newArrayList();
    private List<Product> products = Lists.newArrayList();
    private List<ContentGroup> contentGroups = Lists.newArrayList();
    private Set<String> sameAs = Sets.newHashSet();
    private List<Person> people = Lists.newArrayList();
    private String mediaType;
    private String specialization;
    private boolean scheduleOnly = false;
    private String presentationChannel;

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
        this.genres = Sets.newHashSet(genres);
    }

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "tags")
    @XmlElement(namespace = PLAY_SIMPLE_XML.NS, name = "tag")
    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Iterable<String> tags) {
        this.tags = Sets.newHashSet(tags);
    }

    public PublisherDetails getSource() {
        return source;
    }

    public void setSource(PublisherDetails source) {
        this.source = source;
    }

    public String getImage() {
        return image;
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

    @XmlElementWrapper(namespace = PLAY_SIMPLE_XML.NS, name = "sameAs")
    @XmlElement(name = "uri")
    public Set<String> getSameAs() {
        return sameAs;
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

    protected void copyTo(Description destination) {
        Preconditions.checkNotNull(destination);

        super.copyTo(destination);

        destination.setUri(getUri());
        destination.setTitle(getTitle());
        destination.setDescription(getDescription());

        if (getSource() != null) {
            destination.setSource(getSource().copy());
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
        
        Set<Person> people = Sets.newHashSet();
        for (Person person: this.people) {
            people.add(person.copy());
        }
        destination.setPeople(people);
        
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
}
