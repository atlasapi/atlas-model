package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.BroadcastFlags;

/**
 */
@JsonDeserialize(builder=BroadcastFlags.Builder.class)
public interface BroadcastFlagsConfiguration {
    
}
