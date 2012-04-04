package org.atlasapi.serialization.json;

import org.atlasapi.media.content.RelatedLink;
import org.atlasapi.media.content.RelatedLink.LinkType;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 */
public interface RelatedLinkConfiguration {
    
    @JsonCreator
    RelatedLink make(@JsonProperty("type") LinkType type, @JsonProperty("url") String url);

}
