package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class IdConfiguration {

    @JsonCreator
    IdConfiguration(@JsonProperty("longValue") Long longValue) {
    }
    
}
