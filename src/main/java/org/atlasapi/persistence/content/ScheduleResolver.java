package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Channel;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Schedule;
import org.joda.time.DateTime;

public interface ScheduleResolver {

    Schedule schedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher);
    
}
