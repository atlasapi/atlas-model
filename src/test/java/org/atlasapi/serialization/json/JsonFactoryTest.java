package org.atlasapi.serialization.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Container;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.EntityType;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;
import org.atlasapi.messaging.BeginReplayMessage;
import org.atlasapi.messaging.EndReplayMessage;
import org.atlasapi.messaging.EntityUpdatedMessage;
import org.atlasapi.messaging.Message;
import org.atlasapi.messaging.ReplayMessage;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.serialization.json.configuration.model.FilteredContainerConfiguration;
import org.atlasapi.serialization.json.configuration.model.FilteredItemConfiguration;
import org.joda.time.DateTime;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

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
        item.setAliasUrls(ImmutableList.of("alias1","alias2"));
        item.setId(Id.valueOf(1234L));
        LookupEntry entry = LookupEntry.lookupEntryFrom(item);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        
        String json = mapper.writeValueAsString(entry);
        LookupEntry read = mapper.readValue(json, LookupEntry.class);
        assertEquals(entry, read);
    }
}
