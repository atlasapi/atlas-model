package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class SubtitlesConfiguration {

    @JsonCreator
    SubtitlesConfiguration(@JsonProperty("languageCode") String languageCode) {
    }
}
