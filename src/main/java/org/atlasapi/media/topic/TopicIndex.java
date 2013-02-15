package org.atlasapi.media.topic;

import org.atlasapi.content.criteria.NodeSet;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.query.Selection;

public interface TopicIndex {

    void index(Topic topic);
    
    ListenableFuture<FluentIterable<Id>> query(NodeSet query, 
        Iterable<Publisher> publishers, Selection selection);
    
}
