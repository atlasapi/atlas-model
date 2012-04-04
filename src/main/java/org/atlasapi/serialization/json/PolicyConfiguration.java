package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Location;
import org.atlasapi.media.content.item.Policy;

/**
 */
@JsonDeserialize(builder=Policy.Builder.class)
public interface PolicyConfiguration {
    
}
