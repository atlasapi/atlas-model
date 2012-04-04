package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Episode;

/**
 */
@JsonDeserialize(builder=Episode.Builder.class)
public interface EpisodeConfiguration {
    
}
