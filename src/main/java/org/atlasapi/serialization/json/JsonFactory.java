package org.atlasapi.serialization.json;

import org.atlasapi.equiv.ContentRef;
import org.atlasapi.equiv.EquivalenceSummary;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.RelatedLink;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import java.util.Collection;
import java.util.Collections;
import org.atlasapi.serialization.json.configuration.messaging.MessagingModule;
import org.atlasapi.serialization.json.configuration.model.FilteredContainerConfiguration;
import org.atlasapi.serialization.json.configuration.model.FilteredItemConfiguration;
import org.atlasapi.serialization.json.configuration.model.ModelModule;
import org.joda.time.LocalDateTime;
import java.util.Collections;
import org.atlasapi.serialization.json.configuration.model.FilteredContainerConfiguration;
import org.atlasapi.serialization.json.configuration.model.FilteredItemConfiguration;
import org.atlasapi.serialization.json.configuration.model.ModelModule;
import org.joda.time.LocalDateTime;

/**
 */
public class JsonFactory {
    
    public static ObjectMapper makeJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS, SerializationFeature.WRITE_NULL_MAP_VALUES);
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new GuavaModule());
        mapper.registerModule(new GenericModule());
        mapper.registerModule(new ModelModule());
        mapper.registerModule(new MessagingModule());
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.CREATOR, Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
        //
        FilterProvider filters = new SimpleFilterProvider().
                addFilter(FilteredItemConfiguration.FILTER, SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET)).
                addFilter(FilteredContainerConfiguration.FILTER, SimpleBeanPropertyFilter.serializeAllExcept(Collections.EMPTY_SET));
        mapper.setFilters(filters);
        //
        return mapper;
    }
    
    private static class GenericModule extends SimpleModule {
        
        public GenericModule() {
            super("Generic Module", new com.fasterxml.jackson.core.Version(0, 0, 1, null, null, null));
        }
        
        @Override
        public void setupModule(SetupContext context) {
            addKeyDeserializer(Publisher.class, new PublisherConfiguration.PublisherKeyDeserializer());
            
            super.setupModule(context);
            context.setMixInAnnotations(Object.class, ObjectConfiguration.class);
        }
    }
    
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
    private static interface ObjectConfiguration {
    }
}
