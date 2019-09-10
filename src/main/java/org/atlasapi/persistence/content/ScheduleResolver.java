package org.atlasapi.persistence.content;

import com.google.common.base.Optional;
import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Schedule;
import org.joda.time.DateTime;

import java.util.Set;

public interface ScheduleResolver {

    Schedule schedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher, Optional<Application> mergeApplication);

    Schedule unmergedSchedule(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publisher);
    
    Schedule schedule(DateTime from, int count, Iterable<Channel> channels, Iterable<Publisher> publisher, Optional<Application> mergeApplication);

    Set<Item> resolveItems(DateTime from, DateTime to, Iterable<Channel> channels, Iterable<Publisher> publishers);
    
}
