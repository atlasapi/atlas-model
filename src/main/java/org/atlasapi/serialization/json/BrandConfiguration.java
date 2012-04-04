package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.container.Brand;

/**
 */
@JsonDeserialize(builder=Brand.Builder.class)
public interface BrandConfiguration {
    
}
