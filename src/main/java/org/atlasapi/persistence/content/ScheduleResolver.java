package org.atlasapi.persistence.content;

import org.atlasapi.application.v3.ApplicationConfiguration;
import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Schedule;
import org.joda.time.DateTime;

import com.google.common.base.Optional;

public interface ScheduleResolver {

    Schedule schedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher, Optional<ApplicationConfiguration> mergeConfig);

    Schedule unmergedSchedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher);
    
    Schedule schedule(DateTime from, int count, Iterable<Channel> channels, Iterable<Publisher> publisher, Optional<ApplicationConfiguration> mergeConfig);
    
}
