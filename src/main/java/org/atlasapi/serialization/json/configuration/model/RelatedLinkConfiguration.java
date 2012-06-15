package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.RelatedLink.LinkType;

/**
 */
public abstract class RelatedLinkConfiguration {
    
    @JsonCreator
    public RelatedLinkConfiguration(@JsonProperty("type") LinkType type, @JsonProperty("url") String url) {
    }
}
