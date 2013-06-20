package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;


public class HistoricalChannelGroupEntryTest {

    @Test
    public void testEqualityOnDate() {
        LocalDate date = new LocalDate();
        
        HistoricalChannelEntry entry1 = new HistoricalChannelEntry(date);
        entry1.setTitle("title1");
        
        HistoricalChannelEntry entry2 = new HistoricalChannelEntry(date);
        entry2.setTitle("title1");
        
        assertTrue(entry1.equals(entry2));
        
        HistoricalChannelEntry entry3 = new HistoricalChannelEntry(date.plusDays(1));
        entry3.setTitle("title1");
        
        assertFalse(entry1.equals(entry3));
    }
}
