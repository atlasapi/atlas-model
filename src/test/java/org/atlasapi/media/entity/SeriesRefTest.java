package org.atlasapi.media.entity;

import static org.junit.Assert.assertFalse;

import java.util.Collections;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.metabroadcast.common.scheduling.UpdateProgress;
import com.metabroadcast.common.time.DateTimeZones;


public class SeriesRefTest {
    
    @Test
    public void testOrderingSeriesRefs() {
        ImmutableList<SeriesRef> refs = ImmutableList.of(
            new SeriesRef(1L, "uri1", "Series 9 Reversions", 12, aDateTime()),
            new SeriesRef(2L, "uri2", "Series 9", 9, aDateTime()),
            new SeriesRef(3L, "uri3", "Series 8 Reversions", null, aDateTime()),
            new SeriesRef(4L, "uri4", "Series 11", 11, aDateTime()),
            new SeriesRef(5L, "uri5", "Series 8", 8, aDateTime()),
            new SeriesRef(6L, "uri6", "Series 7 Reversions", null, aDateTime()),
            new SeriesRef(7L, "uri7", "Series 7", 7, aDateTime()),
            new SeriesRef(8L, "uri8", "Series 6", 6, aDateTime()),
            new SeriesRef(9L, "uri9", "Series 5", 5, aDateTime())
        );
        
        UpdateProgress progress = UpdateProgress.START;
        List<SeriesRef> one = null;
        
        for (int i = 0; i < 100; i++) {

            one = Lists.newArrayList(refs);
            List<SeriesRef> two = Lists.newArrayList(refs);
            
            Collections.shuffle(one);
            Collections.shuffle(two);
            
            one = SeriesRef.dedupeAndSort(one);
            two = SeriesRef.dedupeAndSort(two);
            
            if(one.equals(two)) {
                progress = progress.reduce(UpdateProgress.SUCCESS);
            } else {
                progress = progress.reduce(UpdateProgress.FAILURE);
            }
        }
        
        assertFalse(progress.toString(), progress.hasFailures());
        System.out.println(one);
        
    }

    private DateTime aDateTime() {
        return new DateTime(DateTimeZones.UTC);
    }

}
