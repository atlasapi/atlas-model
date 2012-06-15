package org.atlasapi.persistence.topic.util;

import org.atlasapi.media.topic.Topic;
import org.atlasapi.media.topic.TopicStore;

import com.metabroadcast.common.base.Maybe;

public abstract class ForwardingTopicStore implements TopicStore {

    protected ForwardingTopicStore() {}
    
    protected abstract TopicStore delegate();
    
    @Override
    public Maybe<Topic> topicFor(String namespace, String value) {
        return delegate().topicFor(namespace, value);
    }
    
    @Override
    public void write(Topic topic) {
        delegate().write(topic);
    }
    
}
