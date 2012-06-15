package org.atlasapi.persistence.content.schedule;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.media.content.Schedule;
import org.joda.time.DateTime;

public interface ScheduleResolver {

    Schedule schedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher);
    
}
