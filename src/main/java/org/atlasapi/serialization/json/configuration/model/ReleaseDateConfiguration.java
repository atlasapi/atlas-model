package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.entity.ReleaseDate;
import org.joda.time.LocalDate;

/**
 */
public abstract class ReleaseDateConfiguration {

    @JsonCreator
    ReleaseDateConfiguration(@JsonProperty("date") LocalDate date, @JsonProperty("country") Country country, @JsonProperty("type") ReleaseDate.ReleaseType type) {
    }
}
