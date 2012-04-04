package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.container.Series;

/**
 */
@JsonDeserialize(builder=Series.Builder.class)
public interface SeriesConfiguration {
    
}
