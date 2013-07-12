package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;


public class ChannelHistoryOrderingTest {
    
    private final Channel channel = new Channel();

    @Test
    public void testOrdersHistoryEntriesByDate() {
        
        LocalDate now = new LocalDate();
        
        HistoricalChannelEntry current = new HistoricalChannelEntry(now);
        
        HistoricalChannelEntry previous = new HistoricalChannelEntry(now.minusYears(1));
        
        channel.setHistory(ImmutableSet.of(current, previous));
        
        assertEquals(previous, channel.getHistory().get(0));
        assertEquals(current, channel.getHistory().get(1));
    }

}
