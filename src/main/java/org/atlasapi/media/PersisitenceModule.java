package org.atlasapi.media;

import org.atlasapi.media.content.ContentStore;
import org.atlasapi.media.topic.TopicStore;

public interface PersisitenceModule {

    ContentStore contentStore();
    
    TopicStore topicStore();
    
}
