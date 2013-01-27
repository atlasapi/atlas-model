package org.atlasapi.serialization.json.configuration.model;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.persistence.content.ContentCategory;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public abstract class LookupRefConfiguration {

    @JsonCreator
    LookupRefConfiguration(@JsonProperty("id") Id id, @JsonProperty("publisher") Publisher publisher, @JsonProperty("category") ContentCategory category) {
    }
}
