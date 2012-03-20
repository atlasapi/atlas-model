package org.atlasapi.persistence.topic;

import org.atlasapi.media.entity.Topic;

public interface TopicWriter {

    void write(Topic topic);

}
