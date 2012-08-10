package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.EntityType;
import org.joda.time.DateTime;

/**
 */
public abstract class ChildRefConfiguration {
    
    @JsonCreator
    ChildRefConfiguration (
            @JsonProperty("uri") String uri, 
            @JsonProperty("sortKey") String sortKey, 
            @JsonProperty("updated") DateTime updated,
            @JsonProperty("type") EntityType type
    ) {}
    
}
