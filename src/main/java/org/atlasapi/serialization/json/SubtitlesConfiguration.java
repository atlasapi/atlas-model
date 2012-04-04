package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.content.item.Subtitles;

/**
 */
public interface SubtitlesConfiguration {
    
    @JsonCreator
    Subtitles make(@JsonProperty("languageCode") String languageCode);
}
