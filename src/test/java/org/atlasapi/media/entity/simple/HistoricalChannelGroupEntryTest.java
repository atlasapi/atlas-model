package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;


public class HistoricalChannelGroupEntryTest {

    @Test
    public void testEqualityOnDate() {
        LocalDate date = new LocalDate();
        
        HistoricalChannelEntry entry1 = new HistoricalChannelEntry();
        entry1.setTitle("title1");
        entry1.setStartDate(date);
        
        HistoricalChannelEntry entry2 = new HistoricalChannelEntry();
        entry2.setTitle("title1");
        entry2.setStartDate(date);
        
        assertTrue(entry1.equals(entry2));
        
        entry2.setTitle("title1");
        entry2.setStartDate(date.plusDays(1));
        
        assertFalse(entry1.equals(entry2));
    }
}
