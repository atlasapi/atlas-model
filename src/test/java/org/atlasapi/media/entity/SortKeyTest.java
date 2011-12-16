package org.atlasapi.media.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    public void testAdapterKey() {
        String givenKey = "asdf";
        String key = SortKey.keyFrom(new Item("adapter","adapterCurie", Publisher.BBC).withSortKey(givenKey));
        assertThat(key, is("95asdf"));
    }
    
    @Test
    public void testSequenceKey() {
        Episode episode = new Episode("episode", "episodeCurie", Publisher.BBC);

        episode.setSeriesNumber(4);
        episode.setEpisodeNumber(5);
        assertThat(SortKey.keyFrom(episode), is("85000004000005"));
        
        episode.setSeriesNumber(2011);
        episode.setEpisodeNumber(58);
        assertThat(SortKey.keyFrom(episode), is("85002011000058"));
    }
    
    @Test
    public void testBroadcastKey() {
        Item broadcast = new Item("broadcast", "broadcastCurie", Publisher.BBC);
        Version version = new Version();
        version.addBroadcast(new Broadcast("channel10", new DateTime(300, DateTimeZone.UTC), new DateTime(400, DateTimeZone.UTC)));
        version.addBroadcast(new Broadcast("channel9", new DateTime(100, DateTimeZone.UTC), new DateTime(200, DateTimeZone.UTC)));
        broadcast.addVersion(version);
        
        String key = SortKey.keyFrom(broadcast);
        assertThat(key, is("750000000000000000100"));
    }
    
    @Test
    public void testDefaultKey() {
        Item deflt = new Item("default", "defaultCurie", Publisher.BBC);

        String key = SortKey.keyFrom(deflt);
        assertThat(key, is("11"));
    }

    @Test
    public void testNaturalKeyOrdering() {
        
        Item adapter1 = new Item("adapter","adapterCurie", Publisher.BBC).withSortKey("5000");
        Item adapter2 = new Item("adapter","adapterCurie", Publisher.BBC).withSortKey("1000");
        
        Episode episode = new Episode("episode", "episodeCurie", Publisher.BBC);
        episode.setSeriesNumber(4);
        episode.setEpisodeNumber(5);
        
        Episode episode1 = new Episode("episode1", "episodeCurie", Publisher.BBC);
        episode1.setSeriesNumber(1);
        episode1.setEpisodeNumber(6);
        
        Item broadcast = new Item("broadcast", "broadcastCurie", Publisher.BBC);
        Version version = new Version();
        version.addBroadcast(new Broadcast("channel9", new DateTime(2011, 10, 10, 0, 0,0,0, DateTimeZone.UTC), new DateTime(2011, 10, 10, 1, 0,0,0, DateTimeZone.UTC)));
        broadcast.addVersion(version);

        Item broadcast1 = new Item("broadcast", "broadcastCurie", Publisher.BBC);
        Version version1 = new Version();
        version1.addBroadcast(new Broadcast("channel10", new DateTime(2011, 10, 9, 0, 0,0,0, DateTimeZone.UTC), new DateTime(2011, 10, 9, 1, 0,0,0, DateTimeZone.UTC)));
        broadcast1.addVersion(version1);
        
        Item deflt = new Item("default", "defaultCurie", Publisher.BBC);
        
        List<String> orderedKeys = Lists.transform(ImmutableList.of(adapter1, adapter2, episode, episode1, broadcast, broadcast1, deflt), TO_SORT_KEY);
        List<String> randomKeys = Lists.newArrayList(orderedKeys);
        
        Ordering<String> ordering = Ordering.natural().reverse();
        
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(randomKeys);
            assertEquals(orderedKeys, ordering.immutableSortedCopy(randomKeys));
        }
    }

    @Test
    public void testSortKeyComparatorForOldKeysOrdering() {
        
        String oldAdapter1 = "10999";
        String oldAdapter2 = "10888";
        
        String oldSequence1 = "2045";
        String oldSequence2 = "2097";
        
        String oldBroadcast1 = "30100";
        String oldBroadcast2 = "30800";
        
        String oldDefault1 = "99";
        
        List<String> orderedKeys = ImmutableList.of(oldAdapter1, oldAdapter2, oldBroadcast2, oldBroadcast1, oldSequence2, oldSequence1, oldDefault1);
        List<String> randomKeys = Lists.newArrayList(orderedKeys);
        
        Ordering<String> sortKeyOrdering = Ordering.from(new SortKey.SortKeyOutputComparator());

        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(randomKeys, rnd);
            assertEquals(orderedKeys, sortKeyOrdering.immutableSortedCopy(randomKeys));
        }
    }
    
    @Test
    public void testSortKeyComparatorForMixedKeysOrdering() {
        
        String oldAdapter1 = "10999";
        String oldAdapter2 = "10333";
        
        String oldSequence1 = "2045";
        String oldSequence2 = "2097";
        
        String oldBroadcast1 = "30100";
        String oldBroadcast2 = "30800";
        
        String oldDefault1 = "99";
        
        String newAdapter1 = "951000";
        String newAdapter2 = "955000";
        
        String newSequence1 = "85000006000005";
        String newSequence2 = "85002011000058";
        
        String newBroadcast1 = "75200";
        String newBroadcast2 = "75900";
        
        String newDefault1 = "11";

        List<String> orderedKeys = ImmutableList.of(
                oldAdapter1, newAdapter2, oldAdapter2, newAdapter1,
                newBroadcast2, oldBroadcast2, newBroadcast1, oldBroadcast1,
                oldSequence2, oldSequence1, newSequence2, newSequence1, 
                newDefault1, oldDefault1);
        List<String> randomKeys = Lists.newArrayList(orderedKeys);
        
        Ordering<String> sortKeyOrdering = Ordering.from(new SortKey.SortKeyOutputComparator());
        
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(randomKeys, rnd);
            assertEquals(orderedKeys, sortKeyOrdering.immutableSortedCopy(randomKeys));
        }
    }
    
    @Test
    /**
     * We are now preferring broadcast ordering over series and
     * episode number ordering, because we want specials of brands which
     * have both one-offs and series/episodes to be ordered before, rather
     * than after, series/episodes
     */
    public void testSortKeyComparatorForBroadcastSwitchOrdering() {
    	
    	String sortKey1 = "85000006000004";
    	String sortKey2 = "85000006000005";
    	
    	String sortKey3 = "752034";
    	String sortKey4 = "752312";
    	
    	List<String> orderedKeys = ImmutableList.of(
    			sortKey4, sortKey3, sortKey2, sortKey1);
    	
    	List<String> randomKeys = Lists.newArrayList(orderedKeys);
        
        Ordering<String> sortKeyOrdering = Ordering.from(new SortKey.SortKeyOutputComparator());
        
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            Collections.shuffle(randomKeys, rnd);
            assertEquals(orderedKeys, sortKeyOrdering.immutableSortedCopy(randomKeys));
        }
    			
    }
}
