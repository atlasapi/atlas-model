package org.atlasapi.media.entity;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.metabroadcast.common.time.DateTimeZones;


public class ScheduleTest {
    
    private final DateTime now = new DateTime(DateTimeZones.UTC);
    private final Item item = new Item("uri1", "curi1", Publisher.BBC);
    private final Broadcast oldBroadcast = new Broadcast(Channel.MORE_FOUR.uri(), now.minusDays(2), now.minusDays(2).plusHours(1));
    private final Broadcast newBroadcast = new Broadcast(Channel.MORE_FOUR.uri(), now, now.plusHours(1));
    
    @Before
    public void setUp() {
        Version version = new Version();
        version.addBroadcast(oldBroadcast);
        version.addBroadcast(newBroadcast);
        item.addVersion(version);
    }

    @Test
    public void shouldFilterOldBrodcasts() {
        Schedule schedule = Schedule.fromItems(new Interval(now.minusHours(1), now.plusHours(2)), ImmutableList.of(item));
        assertEquals(1, schedule.getEntriesForOnlyChannel().size());
    }
}
