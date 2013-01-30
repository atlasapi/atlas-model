package org.atlasapi.media.topic;

import org.atlasapi.media.util.WriteResult;

public interface TopicWriter {

    WriteResult<Topic> writeTopic(Topic topic);

}
