package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collection;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;
import org.atlasapi.serialization.json.configuration.model.FilteredItemConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class JsonFactoryTest {

    @Test
    public void testUnfilteredItem() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);
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
        item.setId(1L);
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
    public void testItemCanBeReadBackAsGenericContent() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);

        ObjectMapper mapper = JsonFactory.makeJsonMapper();

        String json = mapper.writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertTrue(read instanceof Item);
    }
}
