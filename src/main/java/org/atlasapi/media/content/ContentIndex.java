package org.atlasapi.media.content;

import org.atlasapi.content.criteria.AttributeQuerySet;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.query.Selection;

public interface ContentIndex {

    ListenableFuture<FluentIterable<Id>> query(AttributeQuerySet query, 
        Iterable<Publisher> publishers, Selection selection);
    
}
