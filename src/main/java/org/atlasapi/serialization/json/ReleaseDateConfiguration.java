package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.content.item.ReleaseDate;
import org.atlasapi.media.content.item.ReleaseDate.ReleaseType;
import org.joda.time.LocalDate;

/**
 */
public interface ReleaseDateConfiguration {
    
    @JsonCreator
    ReleaseDate make(@JsonProperty("date") LocalDate date, @JsonProperty("country") Country country, @JsonProperty("type") ReleaseType type);
}
