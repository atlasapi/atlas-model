package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Film;

/**
 */
@JsonDeserialize(builder=Film.Builder.class)
public interface FilmConfiguration {
    
}
