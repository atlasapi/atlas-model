package org.atlasapi.media.topic;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Resolved;
import org.atlasapi.media.util.WriteResult;

import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.collect.OptionalMap;

public abstract class ForwardingTopicStore implements TopicStore {

    protected ForwardingTopicStore() { }
    
    protected abstract TopicStore delegate();
    
    @Override
    public ListenableFuture<Resolved<Topic>> resolveIds(Iterable<Id> ids) {
        return delegate().resolveIds(ids);
    }

    @Override
    public OptionalMap<String, Topic> resolveAliases(Iterable<String> aliases, Publisher source) {
        return delegate().resolveAliases(aliases, source);
    }

    @Override
    public WriteResult<Topic> writeTopic(Topic topic) {
        return delegate().writeTopic(topic);
    }

}
