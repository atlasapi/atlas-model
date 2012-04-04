package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class  ContainerIdentifierConfiguration {
    
    @JsonCreator
    ContainerIdentifierConfiguration(@JsonProperty("uri") String uri) {
    }
}
