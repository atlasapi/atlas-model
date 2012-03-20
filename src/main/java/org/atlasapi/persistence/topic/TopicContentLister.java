package org.atlasapi.persistence.topic;

import java.util.Iterator;

import org.atlasapi.content.criteria.ContentQuery;
import org.atlasapi.media.entity.Content;

public interface TopicContentLister {

    Iterator<Content> contentForTopic(Long topicId, ContentQuery contentQuery); 
    
}
