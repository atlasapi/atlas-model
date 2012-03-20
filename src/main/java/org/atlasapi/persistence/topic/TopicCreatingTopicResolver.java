package org.atlasapi.persistence.topic;

import org.atlasapi.media.entity.Topic;

import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.ids.IdGenerator;

public class TopicCreatingTopicResolver extends ForwardingTopicStore {

    private final IdGenerator idGenerator;
    private final TopicStore delegate;

    public TopicCreatingTopicResolver(TopicStore delegate, IdGenerator idGenerator) {
        this.delegate = delegate;
        this.idGenerator = idGenerator;
    }
    
    public Maybe<Topic> topicFor(String namespace, String value) {
        Maybe<Topic> topic = delegate().topicFor(namespace, value);
        if(topic.hasValue()) {
            return topic;
        } else {
            Topic newTopic = new Topic(idGenerator.generateRaw());
            newTopic.setNamespace(namespace);
            newTopic.setValue(value);
            return Maybe.just(newTopic);
        }
    }

    @Override
    protected TopicStore delegate() {
        return delegate;
    }
        
}
