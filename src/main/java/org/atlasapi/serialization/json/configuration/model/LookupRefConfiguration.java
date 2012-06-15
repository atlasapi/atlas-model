package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.persistence.content.ContentCategory;

/**
 */
public abstract class LookupRefConfiguration {

    @JsonCreator
    LookupRefConfiguration(@JsonProperty("id") String id, @JsonProperty("publisher") Publisher publisher, @JsonProperty("category") ContentCategory category) {
    }
}
