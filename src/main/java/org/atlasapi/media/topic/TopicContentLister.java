package org.atlasapi.media.topic;

import java.util.Iterator;

import org.atlasapi.content.criteria.ContentQuery;
import org.atlasapi.media.content.Content;

public interface TopicContentLister {

    Iterator<Content> contentForTopic(Long topicId, ContentQuery contentQuery); 
    
}
