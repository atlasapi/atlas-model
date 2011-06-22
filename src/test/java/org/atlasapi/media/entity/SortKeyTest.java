package org.atlasapi.media.entity;

import static org.junit.Assert.*;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class SortKeyTest {

    private static final Function<Item, String> TO_SORT_KEY = new Function<Item, String>() {
    
                @Override
                public String apply(Item input) {
                    return SortKey.keyFrom(input);
                }
            };

    @Test
    public void testKeyFrom() {
        
        Item adapter = new Item("adapter","adapterCurie", Publisher.BBC).withSortKey("asdf");
        
        Episode episode = new Episode("episode", "episodeCurie", Publisher.BBC);
        episode.setSeriesNumber(4);
        episode.setEpisodeNumber(5);
        
        Item broadcast = new Item("broadcast", "broadcastCurie", Publisher.BBC);
        Version version = new Version();
        version.addBroadcast(new Broadcast("channel9", new DateTime(2011, 10, 10, 0, 0,0,0, DateTimeZone.UTC), new DateTime(2011, 10, 10, 1, 0,0,0, DateTimeZone.UTC)));
        version.addBroadcast(new Broadcast("channel10", new DateTime(2011, 10, 9, 0, 0,0,0, DateTimeZone.UTC), new DateTime(2011, 10, 9, 1, 0,0,0, DateTimeZone.UTC)));
        broadcast.addVersion(version);
        
        Item deflt = new Item("default", "defaultCurie", Publisher.BBC);
        
        List<Item> randomItems = ImmutableList.of(episode, deflt, broadcast, adapter);
        
        List<String> randomKeys = Lists.transform(randomItems, TO_SORT_KEY);
        
        List<Item> orderedItems = ImmutableList.of(adapter, episode, broadcast, deflt);
        
        List<String> orderedKeys = Lists.transform(orderedItems, TO_SORT_KEY);
        
        assertEquals(orderedKeys, Ordering.natural().immutableSortedCopy(randomKeys));
        
    }

    
    
}
