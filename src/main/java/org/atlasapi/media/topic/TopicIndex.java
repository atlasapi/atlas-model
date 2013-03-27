package org.atlasapi.media.topic;

import org.atlasapi.content.criteria.AttributeQuerySet;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.IndexException;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.query.Selection;

public interface TopicIndex {

    void index(Topic topic) throws IndexException;
    
    ListenableFuture<FluentIterable<Id>> query(AttributeQuerySet query, 
        Iterable<Publisher> publishers, Selection selection);
    
}
