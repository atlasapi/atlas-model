package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.EntityType;
import org.joda.time.DateTime;

/**
 */
public interface ChildRefConfiguration {
    
    @JsonCreator
    ChildRef make(@JsonProperty("uri") String uri, @JsonProperty("sortKey") String sortKey, @JsonProperty("updated") DateTime updated, @JsonProperty("type") EntityType type);
}
