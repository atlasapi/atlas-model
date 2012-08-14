package org.atlasapi.serialization.json.configuration.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.messaging.Message;

/**
 */
public abstract class ReplayMessageConfiguration {

    @JsonCreator
    ReplayMessageConfiguration(
            @JsonProperty("messageId") String messageId,
            @JsonProperty("timestamp") Long timestamp,
            @JsonProperty("entityId") String entityId,
            @JsonProperty("entityType") String entityType,
            @JsonProperty("entitySource") String entitySource,
            @JsonProperty("original") Message original) {
        
    }
    
}
