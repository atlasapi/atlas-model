package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class CountryConfiguration {

    @JsonCreator
    CountryConfiguration(@JsonProperty("code") String code, @JsonProperty("name") String name) {
    }
}
