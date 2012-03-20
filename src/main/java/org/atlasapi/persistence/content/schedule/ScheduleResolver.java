package org.atlasapi.persistence.content.schedule;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Schedule;
import org.joda.time.DateTime;

public interface ScheduleResolver {

    Schedule schedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher);
    
}
