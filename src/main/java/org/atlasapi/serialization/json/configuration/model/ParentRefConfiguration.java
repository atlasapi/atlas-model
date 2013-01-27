package org.atlasapi.serialization.json.configuration.model;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.EntityType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class  ParentRefConfiguration {
    
    @JsonCreator
    ParentRefConfiguration(@JsonProperty("id") Id uri, @JsonProperty("type") EntityType type) {
    }
}
