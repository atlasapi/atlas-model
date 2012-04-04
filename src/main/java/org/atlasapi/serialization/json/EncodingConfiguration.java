package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Encoding;

/**
 */
@JsonDeserialize(builder=Encoding.Builder.class)
public interface EncodingConfiguration {
    
}
