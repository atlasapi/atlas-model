package org.atlasapi.media.topic;

import org.atlasapi.media.common.Id;
import org.joda.time.Interval;

import com.google.common.collect.FluentIterable;
import com.google.common.util.concurrent.ListenableFuture;
import com.metabroadcast.common.query.Selection;

public interface PopularTopicIndex {
   
    ListenableFuture<FluentIterable<Id>> popularTopics(Interval interval, Selection selection);
    
}
