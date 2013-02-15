package org.atlasapi.media;

import org.atlasapi.media.content.ContentIndexer;
import org.atlasapi.media.content.ContentSearcher;
import org.atlasapi.media.content.schedule.ScheduleIndex;
import org.atlasapi.media.topic.PopularTopicIndex;



public interface IndexModule {

    public ContentIndexer contentIndexer();

    public ScheduleIndex scheduleIndex();

    public PopularTopicIndex topicSearcher();
    
    public ContentSearcher contentSearcher();
    
}
