package org.atlasapi.media.entity;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Sets;


public class ScheduleEntryTest {
    
    @Test
    public void testItemComparator() {
        Item item1 = new Item();
        item1.setId(Long.valueOf(123456));
        item1.setVersions(singleVersionWithBroadcast(new Broadcast("xxxxxx", new DateTime(2014, 3, 9, 18, 30), new DateTime(2014, 3, 9, 18, 30))));

        Item item2 = new Item();
        item2.setId(Long.valueOf(123456));
        item2.setVersions(singleVersionWithBroadcast(new Broadcast("yyyyyy", new DateTime(2014, 3, 9, 18, 30), new DateTime(2014, 3, 9, 19, 00))));
        
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item1, item2), is(lessThan(0)));
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item2, item1), is(greaterThan(0)));

        Item item3 = new Item();
        item3.setId(Long.valueOf(123456));
        item3.setVersions(singleVersionWithBroadcast(new Broadcast("xxxxxx", new DateTime(2014, 3, 9, 17, 30), new DateTime(2014, 3, 9, 18, 30))));
        
        Item item4 = new Item();
        item4.setId(Long.valueOf(123456));
        item4.setVersions(singleVersionWithBroadcast(new Broadcast("yyyyyy", new DateTime(2014, 3, 9, 18, 30), new DateTime(2014, 3, 9, 19, 00))));
        
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item3, item4), is(lessThan(0)));
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item4, item3), is(greaterThan(0)));

        Item item5 = new Item();
        item5.setId(Long.valueOf(123456));
        item5.setVersions(singleVersionWithBroadcast(new Broadcast("xxxxxx", new DateTime(2014, 3, 9, 18, 30), new DateTime(2014, 3, 9, 19, 00))));
        
        Item item6 = new Item();
        item6.setId(Long.valueOf(123456));
        item6.setVersions(singleVersionWithBroadcast(new Broadcast("yyyyyy", new DateTime(2014, 3, 9, 18, 30), new DateTime(2014, 3, 9, 19, 00))));
        
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item5, item6), is(equalTo(0)));
        assertThat(ScheduleEntry.START_TIME_AND_DURATION_ITEM_COMPARATOR.compare(item6, item5), is(equalTo(0)));
    }
    
    private Set<Version> singleVersionWithBroadcast(Broadcast broadcast) {
        Version version = new Version();
        version.setBroadcasts(Sets.newHashSet(broadcast));
        
        return Sets.newHashSet(version);
    }

}
