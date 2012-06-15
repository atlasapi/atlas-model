package org.atlasapi.media.topic;


import com.metabroadcast.common.base.Maybe;

public interface TopicLookupResolver {
    
    Maybe<Topic> topicFor(String namespace, String value);
    
}
