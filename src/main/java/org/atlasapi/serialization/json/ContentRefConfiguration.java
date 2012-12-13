package org.atlasapi.serialization.json;

import org.atlasapi.media.entity.Publisher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentRefConfiguration {

    @JsonCreator
    public ContentRefConfiguration(
            @JsonProperty("canonicalUri") String canonicalUri,
            @JsonProperty("publisher") Publisher publisher,
            @JsonProperty("parentUri") String parent
    ) {}
    
}
