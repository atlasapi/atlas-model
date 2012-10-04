package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class DescriptionConfiguration {

    @JsonCreator
    DescriptionConfiguration(@JsonProperty("title") String title,
            @JsonProperty("synopsis") String synopsis,
            @JsonProperty("image") String image,
            @JsonProperty("thumbnail") String thumbnail) {
    }
}
