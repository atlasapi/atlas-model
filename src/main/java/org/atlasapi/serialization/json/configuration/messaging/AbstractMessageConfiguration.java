package org.atlasapi.serialization.json.configuration.messaging;

import org.joda.time.DateTime;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class AbstractMessageConfiguration {

    @JsonCreator
    AbstractMessageConfiguration(
            @JsonProperty("messageId") String messageId,
            @JsonProperty("timestamp") Long timestamp,
            @JsonProperty("entityId") String entityId,
            @JsonProperty("entityType") String entityType,
            @JsonProperty("entitySource") String entitySource) {
        
    }
    
}
