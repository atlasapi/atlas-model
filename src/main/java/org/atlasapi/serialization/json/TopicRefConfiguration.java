package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.content.item.Restriction;
import org.atlasapi.media.topic.Topic.TopicIdentifier;

/**
 */
public interface TopicRefConfiguration {
    
    @JsonCreator
    Restriction make(@JsonProperty("topicIdentifier") TopicIdentifier topicIdentifier, @JsonProperty("weighting") Float weighting, @JsonProperty("supervised") Boolean supervised);
}
