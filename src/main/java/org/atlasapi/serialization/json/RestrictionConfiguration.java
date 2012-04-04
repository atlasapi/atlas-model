package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.content.item.Restriction;

/**
 */
public interface RestrictionConfiguration {
    
    @JsonCreator
    Restriction make(@JsonProperty("restricted") Boolean restricted, @JsonProperty("minimumAge") Integer minimumAge, @JsonProperty("message") String message);
}
