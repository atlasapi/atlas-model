package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertEquals;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;


public class ChannelGroupHistoryOrderingTest {
    
    private final ChannelGroup channelGroup = new ChannelGroup();

    @Test
    public void testOrdersHistoryEntriesByDate() {
        
        LocalDate now = new LocalDate();
        
        HistoricalChannelGroupEntry current = new HistoricalChannelGroupEntry();
        current.setStartDate(now);
        
        HistoricalChannelGroupEntry previous = new HistoricalChannelGroupEntry();
        previous.setStartDate(now.minusYears(1));
        
        channelGroup.setHistory(ImmutableSet.of(current, previous));
        
        assertEquals(previous, channelGroup.getHistory().get(0));
        assertEquals(current, channelGroup.getHistory().get(1));
    }

}
