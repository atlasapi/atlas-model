package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.RelatedLink;
import org.atlasapi.media.entity.RelatedLink.LinkType;

/**
 */
public interface RelatedLinkConfiguration {
    
    @JsonCreator
    RelatedLink make(@JsonProperty("type") LinkType type, @JsonProperty("url") String url);
}
