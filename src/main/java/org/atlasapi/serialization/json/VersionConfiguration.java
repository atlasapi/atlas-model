package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Version;

/**
 */
@JsonDeserialize(builder=Version.Builder.class)
public interface VersionConfiguration {
    
}
