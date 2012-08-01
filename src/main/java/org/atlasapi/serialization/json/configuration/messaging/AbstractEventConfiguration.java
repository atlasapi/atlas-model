package org.atlasapi.serialization.json.configuration.messaging;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class AbstractEventConfiguration {

    @JsonCreator
    AbstractEventConfiguration(@JsonProperty("changeId") String changeId, @JsonProperty("entityId") String entityId, @JsonProperty("entityType") String entityType) {
    }
}
