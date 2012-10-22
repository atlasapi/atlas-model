package org.atlasapi.serialization.json.configuration.model;

import java.io.IOException;

import org.atlasapi.media.entity.Publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

public class PublisherConfiguration {

    public static class PublisherKeyDeserializer extends KeyDeserializer {

        @Override
        public Object deserializeKey(String key, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            return Publisher.fromKey(key).requireValue();
        }
        
    }
    
}
