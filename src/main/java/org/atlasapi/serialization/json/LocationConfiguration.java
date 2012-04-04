package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Location;

/**
 */
@JsonDeserialize(builder=Location.Builder.class)
public interface LocationConfiguration {
    
}
