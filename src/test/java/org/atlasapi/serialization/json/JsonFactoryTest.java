package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import java.util.Arrays;
import java.util.Collections;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 */
public class JsonFactoryTest {
    
    @Test
    public void testItemWithNoFiltering() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        
        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET));
        
        String json = mapper.writer(filters).writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertEquals(1, item.getClips().size());
        assertEquals(1, read.getClips().size());
    }
    
    @Test
    public void testItemWithFiltering() throws Exception {
        Item item = new Item("uri", "curie", Publisher.BBC);
        item.setId(1L);
        item.setClips(Arrays.asList(new Clip("uri", "curie", Publisher.BBC)));
        
        ObjectMapper mapper = JsonFactory.makeJsonMapper();
        FilterProvider filters = new SimpleFilterProvider().addFilter("Item", SimpleBeanPropertyFilter.serializeAllExcept("clips"));
        
        String json = mapper.writer(filters).writeValueAsString(item);
        Content read = mapper.readValue(json, Content.class);
        assertEquals(1, item.getClips().size());
        assertEquals(0, read.getClips().size());
    }
}
