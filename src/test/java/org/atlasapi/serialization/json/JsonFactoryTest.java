package org.atlasapi.serialization.json;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Container;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Certificate;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.ContentGroupRef;
import org.atlasapi.media.entity.CrewMember;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.EntityType;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Restriction;
import org.atlasapi.media.entity.Item.ContainerSummary;
import org.atlasapi.media.entity.KeyPhrase;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.MediaType;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.RelatedLink;
import org.atlasapi.media.entity.Specialization;
import org.atlasapi.media.entity.TopicRef;
import org.atlasapi.media.entity.TopicRef.Relationship;
import org.atlasapi.media.entity.Version;
import org.atlasapi.messaging.BeginReplayMessage;
import org.atlasapi.messaging.EndReplayMessage;
import org.atlasapi.messaging.EntityUpdatedMessage;
import org.atlasapi.messaging.Message;
import org.atlasapi.messaging.ReplayMessage;
import org.atlasapi.persistence.content.ContentCategory;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.serialization.json.configuration.model.FilteredContainerConfiguration;
import org.atlasapi.serialization.json.configuration.model.FilteredItemConfiguration;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Countries;
import com.metabroadcast.common.time.DateTimeZones;

/**
 */
public class JsonFactoryTest {
    
    @Test
    public void testUnfilteredItem() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(Id.valueOf(1L));
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        item.setVersions(Sets.newHashSet(new Version()));

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(item);
        Item read = mapper.readValue(json, Item.class);
        assertEquals(1, read.getClips().size());
        assertEquals(1, read.getVersions().size());
    }

    @Test
    public void testFilteredItem() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(Id.valueOf(1L));
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        item.setVersions(Sets.newHashSet(new Version()));

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter(FilteredItemConfiguration.FILTER, SimpleBeanPropertyFilter.serializeAllExcept(FilteredItemConfiguration.CLIPS_FILTER, FilteredItemConfiguration.VERSIONS_FILTER));
        mapper.setFilters(filters);

        String itemJson = mapper.writeValueAsString(item);
        Item readItem = mapper.readValue(itemJson, Item.class);
        assertEquals(0, readItem.getClips().size());
        assertEquals(0, readItem.getVersions().size());

        String clipsJson = mapper.writeValueAsString(item.getClips());
        Collection<Clip> readClips = mapper.readValue(clipsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Clip.class));
        assertEquals(1, readClips.size());

        String versionsJson = mapper.writeValueAsString(item.getVersions());
        Collection<Version> readVersions = mapper.readValue(versionsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Version.class));
        assertEquals(1, readVersions.size());
    }
    
    @Test
    public void testUnfilteredContainer() throws Exception {
        Container container = new Brand("uri", "curie", Publisher.BBC);
        container.setId(Id.valueOf(1L));
        container.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        container.setChildRefs(Arrays.asList(new ChildRef(Id.valueOf(1L), "sort", new DateTime(), EntityType.ITEM)));

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(container);
        Container read = mapper.readValue(json, Container.class);
        assertEquals(1, read.getClips().size());
        assertEquals(1, read.getChildRefs().size());
    }
    
    @Test
    public void testFilteredContainer() throws Exception {
        Container container = new Brand("uri", "curie", Publisher.BBC);
        container.setId(Id.valueOf(1L));
        container.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        container.setChildRefs(Arrays.asList(new ChildRef(Id.valueOf(1L), "sort", new DateTime(), EntityType.ITEM)));

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().
                addFilter(FilteredItemConfiguration.FILTER, SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET)).
                addFilter(FilteredContainerConfiguration.FILTER, SimpleBeanPropertyFilter.serializeAllExcept(FilteredContainerConfiguration.CLIPS_FILTER, FilteredContainerConfiguration.CHILD_REFS_FILTER));
        mapper.setFilters(filters);

        String containerJson = mapper.writeValueAsString(container);
        Container readContainer = mapper.readValue(containerJson, Container.class);
        assertEquals(0, readContainer.getClips().size());
        assertEquals(0, readContainer.getChildRefs().size());

        String clipsJson = mapper.writeValueAsString(container.getClips());
        Collection<Clip> readClips = mapper.readValue(clipsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Clip.class));
        assertEquals(1, readClips.size());

        String childrenJson = mapper.writeValueAsString(container.getChildRefs());
        Collection<ChildRef> readChildren = mapper.readValue(childrenJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, ChildRef.class));
        assertEquals(1, readChildren.size());
    }

    @Test
    public void testItemCanBeReadBackAsGenericContent() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(Id.valueOf(1L));

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertTrue(read instanceof Item);
    }
    
    @Test
    public void testEntityUpdatedMessage() throws Exception {
        Message message = new EntityUpdatedMessage("1", Long.MIN_VALUE, null, null, null);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(message);
        Message read = mapper.readValue(json, Message.class);
        assertEquals(message, read);
    }
    
    @Test
    public void testBeginReplayMessage() throws Exception {
        Message message = new BeginReplayMessage("1", Long.MIN_VALUE, null, null, null);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(message);
        Message read = mapper.readValue(json, Message.class);
        assertEquals(message, read);
    }
    
    @Test
    public void testEndReplayMessage() throws Exception {
        Message message = new EndReplayMessage("1", Long.MIN_VALUE, null, null, null);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(message);
        Message read = mapper.readValue(json, Message.class);
        assertEquals(message, read);
    }
    
    @Test
    public void testReplayMessage() throws Exception {
        Message message = new ReplayMessage("1", Long.MIN_VALUE, null, null, null, null);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(message);
        Message read = mapper.readValue(json, Message.class);
        assertEquals(message, read);
    }
    
    @Test
    public void testLookupEntry() throws Exception {
        Item item = new Item("item", "item", Publisher.BBC);
        item.setAliases(ImmutableList.of("alias1","alias2"));
        item.setId(Id.valueOf(1234L));
        LookupEntry entry = LookupEntry.lookupEntryFrom(item);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        
        String json = mapper.writeValueAsString(entry);
        LookupEntry read = mapper.readValue(json, LookupEntry.class);
        assertEquals(entry, read);
    }
    
    @Test
    public void testSize() throws Exception {
        Episode episode = new Episode();
        setItemProperties(episode);
        episode.setEpisodeNumber(5);
        episode.setPartNumber(4);
        episode.setSeriesNumber(5);
        episode.setSeriesRef(new ParentRef(5, EntityType.SERIES));
        
        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        byte[] serialized;
        Content deserialized = null;
        for (int i = 0; i < 5; i++) {
            long start = System.nanoTime();
            serialized = mapper.writeValueAsBytes(episode);
            long end = System.nanoTime();
            deserialized = mapper.readValue(serialized, Episode.class);
            System.out.println(String.format("%s\t%s\t%s", (end-start)/1000, (System.nanoTime()-end)/1000,serialized.length));
        }
        
        assertThat(deserialized, is(instanceOf(Episode.class)));
        Episode deserializedEpisode = (Episode)deserialized;
//        checkItemProperties(deserializedEpisode, episode);
        assertThat(deserializedEpisode.getEpisodeNumber(), is(episode.getEpisodeNumber()));
        assertThat(deserializedEpisode.getPartNumber(), is(episode.getPartNumber()));
        assertThat(deserializedEpisode.getSeriesNumber(), is(episode.getSeriesNumber()));
        assertThat(deserializedEpisode.getSeriesRef(), is(episode.getSeriesRef()));
  
    }
    
    private void setItemProperties(Item item) {
        setContentProperties(item);
        item.setParentRef(new ParentRef(4321, EntityType.BRAND));
        item.setContainerSummary(new ContainerSummary("brand", "title", "description", null));
        item.setBlackAndWhite(true);
        item.setCountriesOfOrigin(ImmutableSet.of(Countries.GB));
        item.setIsLongForm(true);
        
        Version version = new Version();
        version.setCanonicalUri("version1");       
        version.set3d(true);
        version.setRestriction(Restriction.from(14, "old"));
        version.setDuration(Duration.standardMinutes(4));
        version.setPublishedDuration(600);
        
        addBroadcasts(version);
        
        item.setVersions(ImmutableSet.of(version));
    }

    private void addBroadcasts(Version version) {
        DateTime start = new DateTime(DateTimeZones.UTC);
        for (int i = 0; i < 32; i++) {
            start = start.plusHours(1);
            DateTime end = start.plusHours(1);
            Broadcast broadcast = new Broadcast("channel", start, end);
            broadcast.setId(Id.valueOf(1234+i));
            broadcast.setCanonicalUri("uri"+i);
            broadcast.setAliases(ImmutableSet.of("alias1","alias2"));
            broadcast.setLastUpdated(start);
            
            broadcast.setScheduleDate(null);
            broadcast.withId("sourceId"+i);
            broadcast.setIsActivelyPublished(null);
            broadcast.setRepeat(true);
            broadcast.setSubtitled(false);
            broadcast.setSigned(false);
            broadcast.setAudioDescribed(true);
            broadcast.setHighDefinition(false);
            broadcast.setWidescreen(true);
            broadcast.setSurround(false);
            broadcast.setLive(true);
            broadcast.setNewSeries(false);
            broadcast.setPremiere(true);
            version.addBroadcast(broadcast);
        }
    }

    private void setContentProperties(Content content) {
        setDescribedProperties(content);
        content.setCertificates(ImmutableSet.of(new Certificate("PG", Countries.GB)));
        content.setClips(ImmutableSet.of(new Clip("clip", "clip", Publisher.BBC)));
        content.setContentGroupRefs(ImmutableSet.of(new ContentGroupRef(Id.valueOf(1234), "uri")));
        content.setKeyPhrases(ImmutableSet.of(new KeyPhrase("phrase", null)));
        content.setLanguages(ImmutableSet.of("en"));
        content.setPeople(ImmutableList.of(CrewMember.crewMember("id", "Jim", "director", Publisher.BBC)));
        content.setRelatedLinks(ImmutableSet.of(RelatedLink.twitterLink("twitter").build()));
//        content.setTopicRefs(ImmutableSet.of(new TopicRef(1L, 1.0f, true, Relationship.TRANSCRIPTION)));
        content.setYear(1234);
    }

    private void setDescribedProperties(Described described) {
        setIdentifiedProperties(described);
        described.setPublisher(Publisher.BBC);
        described.setDescription("desc");
        described.setFirstSeen(new DateTime(DateTimeZones.UTC));
        described.setGenres(ImmutableSet.of("genre"));
        described.setImage("image");
//        described.setImages(ImmutableSet.of(new Image("image")));
        described.setLongDescription("longDesc");
        described.setMediaType(MediaType.AUDIO);
        described.setMediumDescription("medDesc");
        described.setPresentationChannel("bbcone");
        described.setScheduleOnly(true);
        described.setShortDescription("shortDesc");
        described.setSpecialization(Specialization.RADIO);
        described.setThisOrChildLastUpdated(new DateTime(DateTimeZones.UTC));
        described.setThumbnail("thumbnail");
        described.setTitle("title");
    }

    private void setIdentifiedProperties(Identified identified) {
        identified.setId(Id.valueOf(1234));
        identified.setLastUpdated(new DateTime(DateTimeZones.UTC));
        identified.setAliases(ImmutableSet.of("alias1","alias2"));
        identified.setCanonicalUri("canonicalUri");
        identified.setEquivalenceUpdate(new DateTime(DateTimeZones.UTC));
        identified.setEquivalentTo(ImmutableSet.of(new LookupRef(Id.valueOf(1),Publisher.BBC, ContentCategory.CHILD_ITEM)));
    }
}
