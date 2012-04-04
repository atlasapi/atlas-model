package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.topic.Topic;
import org.atlasapi.media.topic.TopicCategory;

/**
 */
@JsonDeserialize(builder=Topic.Builder.class)
public interface TopicConfiguration {
    
    @JsonCreator
    Topic make(@JsonProperty("category") TopicCategory category);
}
