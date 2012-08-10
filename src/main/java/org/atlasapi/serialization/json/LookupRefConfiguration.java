package org.atlasapi.serialization.json;

import org.atlasapi.media.entity.Publisher;
import org.atlasapi.persistence.content.ContentCategory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class LookupRefConfiguration {

    @JsonCreator
    public LookupRefConfiguration(
            @JsonProperty("id") String id, 
            @JsonProperty("publisher") Publisher publisher, 
            @JsonProperty("category") ContentCategory category) {
    }
    
}
