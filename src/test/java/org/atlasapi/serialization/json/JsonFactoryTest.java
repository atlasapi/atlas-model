package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.atlasapi.media.content.Clip;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.Item;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.media.content.Version;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class JsonFactoryTest {

    @Test
    public void testItemCanBeReadBackAsGenericContent() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        assertEquals(1, item.getClips().size());

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET));

        String json = mapper.writer(filters).writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertEquals(1, read.getClips().size());
    }

    @Test
    public void testItemWithFiltering() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        item.setVersions(Sets.newHashSet(new Version()));
        assertEquals(1, item.getClips().size());
        assertEquals(1, item.getVersions().size());


        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept("clips", "versions"));

        String itemJson = mapper.writer(filters).writeValueAsString(item);
        Item readItem = mapper.readValue(itemJson, Item.class);
        assertEquals(0, readItem.getClips().size());
        assertEquals(0, readItem.getVersions().size());

        String clipsJson = mapper.writer(filters).writeValueAsString(item.getClips());
        Collection<Clip> readClips = mapper.readValue(clipsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Clip.class));
        assertEquals(1, readClips.size());
        
        String versionsJson = mapper.writer(filters).writeValueAsString(item.getVersions());
        Collection<Version> readVersions = mapper.readValue(versionsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Version.class));
        assertEquals(1, readVersions.size());
    }
}
