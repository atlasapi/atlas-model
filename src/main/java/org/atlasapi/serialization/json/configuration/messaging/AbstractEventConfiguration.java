package org.atlasapi.serialization.json.configuration.messaging;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class AbstractEventConfiguration {

    @JsonCreator
    AbstractEventConfiguration(
            @JsonProperty("changeId") String changeId,
            @JsonProperty("timestamp") DateTime timestamp,
            @JsonProperty("entityId") String entityId,
            @JsonProperty("entityType") String entityType,
            @JsonProperty("entitySource") String entitySource) {
        
    }
    
}
