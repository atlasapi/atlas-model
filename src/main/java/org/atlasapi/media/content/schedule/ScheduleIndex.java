package org.atlasapi.media.content.schedule;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.Interval;

import com.google.common.util.concurrent.ListenableFuture;

public interface ScheduleIndex {

    ListenableFuture<ScheduleRef> resolveSchedule(Publisher publisher, Channel channel, Interval scheduleInterval);
    
}
