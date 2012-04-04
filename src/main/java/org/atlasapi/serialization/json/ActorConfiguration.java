package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Actor;

/**
 */
@JsonDeserialize(builder=Actor.Builder.class)
public interface ActorConfiguration {
    
}
