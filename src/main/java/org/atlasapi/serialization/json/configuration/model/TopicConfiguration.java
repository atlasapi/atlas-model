package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class TopicConfiguration {

    @JsonCreator
    TopicConfiguration(@JsonProperty("id") Long id, @JsonProperty("namespace") String namespace, @JsonProperty("value") String value) {
    }
}
