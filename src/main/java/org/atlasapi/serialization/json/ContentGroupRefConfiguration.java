package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ContentGroupRefConfiguration {

    @JsonCreator
    ContentGroupRefConfiguration(@JsonProperty("id") Long id,
                          @JsonProperty("uri") String uri) {
    }
    
}
