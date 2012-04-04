package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.common.Description;

/**
 */
@JsonDeserialize(builder=Description.Builder.class)
public interface DescriptionConfiguration {
    
}
