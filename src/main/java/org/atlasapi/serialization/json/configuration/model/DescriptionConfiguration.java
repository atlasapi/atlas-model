package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class DescriptionConfiguration {

    @JsonCreator
    DescriptionConfiguration(@JsonProperty("title") String title,
            @JsonProperty("shortSynopsis") String shortSynopsis,
            @JsonProperty("mediumSynopsis") String mediumSynopsis,
            @JsonProperty("longSynopsis") String longSynopsis,
            @JsonProperty("image") String image,
            @JsonProperty("thumbnail") String thumbnail) {
    }
}
