package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.ParentRef;

/**
 */
public interface ParentRefConfiguration {
    
    @JsonCreator
    ParentRef make(@JsonProperty("uri") String uri);
}
