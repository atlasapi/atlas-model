package org.atlasapi.media.topic;

import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public enum TopicCategory {
    
    SUBJECT("subject"),
    PERSON("person"),
    PLACE("place"),
    ARTIST("artist"),
    EVENT("event"),
    PRODUCT("product"),
    WORK("work"),
    UNKNOWN("unknown");
    
    private final String key;

    private TopicCategory(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
    
    @Override
    public String toString() {
        return key;
    }
    
    public static Map<String, Optional<TopicCategory>> TYPE_KEY_LOOKUP = initTopicTypeMap();
    
    public static Optional<TopicCategory> fromKey(String key) {
        Optional<TopicCategory> possibleTopicType = TYPE_KEY_LOOKUP.get(key);
        return possibleTopicType != null ? possibleTopicType : Optional.<TopicCategory>absent();
    }
    
    private static Map<String, Optional<TopicCategory>> initTopicTypeMap() {
        Builder<String,Optional<TopicCategory>> topicTypeMap = ImmutableMap.builder();
        for(TopicCategory topicType : values()) {
            topicTypeMap.put(topicType.key(), Optional.of(topicType));
        }
        return topicTypeMap.build();
    }

    public static TopicCategory fromKey(String key, TopicCategory deflt) {
        Optional<TopicCategory> possibleTopicType = fromKey(key);
        return possibleTopicType.isPresent() ? possibleTopicType.get() : deflt;
    }
}
