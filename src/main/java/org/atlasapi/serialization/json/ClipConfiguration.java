package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Clip;

/**
 */
@JsonDeserialize(builder=Clip.Builder.class)
public interface ClipConfiguration {
    
}
