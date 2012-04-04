package org.atlasapi.serialization.json;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.atlasapi.media.common.Publisher;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.item.Clip;
import org.atlasapi.media.content.item.Item;
import org.atlasapi.media.content.item.Version;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.ImmutableSet;

/**
 */
public class JsonFactoryTest {

    @Test
    public void testItemCanBeReadBackAsGenericContent() throws Exception {
        Item item = Item.builder()
                .withSourceUri("uri")
                .withSourceCurie("curie")
                .withSource(Publisher.BBC)
                .withId(1L)
                .withClips(ImmutableSet.of(
                    Clip.builder()
                        .withSourceUri("uri")
                        .withSourceCurie("curie")
                        .withSource(Publisher.BBC)
                        .build()
                )).build();
        
        assertEquals(1, item.clips().size());

        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept(ImmutableSet.<String>of()));

        String json = mapper.writer(filters).writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertEquals(1, read.clips().size());
    }

    @Test
    public void testItemWithFiltering() throws Exception {
        Item item = Item.builder()
                .withSourceUri("uri")
                .withSourceCurie("curie")
                .withSource(Publisher.BBC)
                .withId(1L)
                .withClips(ImmutableSet.of(
                    Clip.builder()
                        .withSourceUri("uri")
                        .withSourceCurie("curie")
                        .withSource(Publisher.BBC)
                        .build()
                ))
                .withVersions(ImmutableSet.of(
                    Version.builder().build()
                )).build();

        assertEquals(1, item.clips().size());
        assertEquals(1, item.versions().size());


        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept("clips", "versions"));

        String itemJson = mapper.writer(filters).writeValueAsString(item);
        Item readItem = mapper.readValue(itemJson, Item.class);
        assertEquals(0, readItem.clips().size());
        assertEquals(0, readItem.versions().size());

        String clipsJson = mapper.writer(filters).writeValueAsString(item.clips());
        Collection<Clip> readClips = mapper.readValue(clipsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Clip.class));
        assertEquals(1, readClips.size());
        
        String versionsJson = mapper.writer(filters).writeValueAsString(item.versions());
        Collection<Version> readVersions = mapper.readValue(versionsJson, TypeFactory.defaultInstance().constructCollectionType(Collection.class, Version.class));
        assertEquals(1, readVersions.size());

    }
}
