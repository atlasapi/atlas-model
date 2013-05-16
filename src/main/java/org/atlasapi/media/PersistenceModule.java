package org.atlasapi.media;

import org.atlasapi.media.content.ContentStore;
import org.atlasapi.media.content.schedule.ScheduleStore;
import org.atlasapi.media.topic.TopicStore;

public interface PersistenceModule {

    ContentStore contentStore();
    
    TopicStore topicStore();

    ScheduleStore scheduleStore();
    
}
