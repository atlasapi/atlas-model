package org.atlasapi.media.content.schedule;

import static org.atlasapi.media.entity.Publisher.METABROADCAST;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.ChannelSchedule;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;
import org.atlasapi.media.util.ItemAndBroadcast;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.metabroadcast.common.time.DateTimeZones;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleBlockUpdaterTest {

    private final ScheduleBlockUpdater updater = new ScheduleBlockUpdater(); 

    private final Publisher source = Publisher.METABROADCAST;
    private final Channel channel = Channel.builder().build();

    @Before
    public void setUp() {
        channel.setCanonicalUri("channel");
    }
    
    @Test
    public void testNoStaleEntriesWhenPreviousScheduleIsEmpty() throws Exception {
        Interval interval = new Interval(0, 100, DateTimeZones.UTC);
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
                new ChannelSchedule(channel, interval, ImmutableList.<ItemAndBroadcast>of()));
        
        ItemAndBroadcast updateEntry = itemAndBroadcast(1, source, channel, "one", interval);
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, ImmutableList.of(updateEntry), channel, interval);
        
        assertTrue(scheduleUpdate.getStaleEntries().isEmpty());
        assertThat(Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks().get(0).getEntries()), is(updateEntry));
    }
    
    @Test
    public void testNoStaleEntriesWhenPreviousScheduleMatchesCurrent() throws Exception {
        
        Interval interval = new Interval(0, 100, DateTimeZones.UTC);
        ItemAndBroadcast updateEntry = itemAndBroadcast(1, source, channel, "one", interval);
        
        List<ItemAndBroadcast> entries = ImmutableList.of(updateEntry);
        List<ChannelSchedule> currentSchedule = ImmutableList.of(new ChannelSchedule(channel, interval, entries));
        
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, ImmutableList.of(updateEntry), channel, interval);

        assertTrue(scheduleUpdate.getStaleEntries().isEmpty());
        
        ChannelSchedule updatedBlock = Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks());
        assertThat(Iterables.getOnlyElement(updatedBlock.getEntries()), is(updateEntry));
    }

    @Test
    public void testStaleEntryReplacedWhenBroadcastIdChanges() throws Exception {
        Interval interval = new Interval(0, 100, DateTimeZones.UTC);
        
        ItemAndBroadcast currentEntry = itemAndBroadcast(1, METABROADCAST, channel, "one", interval);
        
        ItemAndBroadcast updatedEntry = new ItemAndBroadcast(
            currentEntry.getItem().copy(), 
            currentEntry.getBroadcast().copy().withId("different")
        );
        
        List<ItemAndBroadcast> updatedSchedule = ImmutableList.of(updatedEntry);
        List<ChannelSchedule> currentSchedule = ImmutableList.of(new ChannelSchedule(channel, interval, ImmutableList.of(currentEntry)));
        
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, updatedSchedule, channel, interval);
        
        ChannelSchedule updatedBlock = Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks());
        assertThat(Iterables.getOnlyElement(updatedBlock.getEntries()), is(updatedEntry));
        assertThat(scheduleUpdate.getStaleEntries(), hasItem(currentEntry));
    }

    @Test
    public void testUpdatesWhenBroadcastOverlapUpdateIntervalStart() throws Exception {
        Interval interval = new Interval(50, 150, DateTimeZones.UTC);
        
        ItemAndBroadcast currentEntry = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(0, 100, DateTimeZones.UTC));
        List<ChannelSchedule> currentSchedule = ImmutableList.of(new ChannelSchedule(channel, interval, ImmutableList.of(currentEntry)));
        
        List<ItemAndBroadcast> updateEntries = ImmutableList.of(new ItemAndBroadcast(
            currentEntry.getItem().copy(), 
            currentEntry.getBroadcast().copy().withId("different")
        ));
        
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, updateEntries, channel, interval);
        
        assertThat(scheduleUpdate.getStaleEntries(), hasItem(currentEntry));
    }
    
    @Test
    public void testPutsBroadcastSpanningTwoBlocksInBothBlocks() throws Exception {
        Interval interval1 = new Interval(50, 150, DateTimeZones.UTC);
        Interval interval2 = new Interval(150, 250, DateTimeZones.UTC);

        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, interval1, ImmutableList.<ItemAndBroadcast>of()),
            new ChannelSchedule(channel, interval2, ImmutableList.<ItemAndBroadcast>of())
        );
        
        ItemAndBroadcast updateEntry = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(100, 200, DateTimeZones.UTC));
        
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, ImmutableList.of(updateEntry), channel, new Interval(interval1.getStart(), interval2.getEnd()));
        
        assertThat(Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks().get(0).getEntries()), is(updateEntry));
        assertThat(Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks().get(1).getEntries()), is(updateEntry));
        assertTrue(scheduleUpdate.getStaleEntries().isEmpty());
    }

    @Test
    public void testDoesntPutBroadcastInOneBlockInBothBlocks() throws Exception {
        Interval interval1 = new Interval(0, 100, DateTimeZones.UTC);
        Interval interval2 = new Interval(100, 200, DateTimeZones.UTC);
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, interval1, ImmutableList.<ItemAndBroadcast>of()),
            new ChannelSchedule(channel, interval2, ImmutableList.<ItemAndBroadcast>of())
        );
        
        ItemAndBroadcast updateEntry = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(25, 100, DateTimeZones.UTC));

        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, ImmutableList.of(updateEntry), channel, new Interval(interval1.getStart(), interval2.getEnd()));
        
        assertThat(Iterables.getOnlyElement(scheduleUpdate.getUpdatedBlocks().get(0).getEntries()), is(updateEntry));
        assertTrue(scheduleUpdate.getUpdatedBlocks().get(1).getEntries().isEmpty());

        assertTrue(scheduleUpdate.getStaleEntries().isEmpty());
    }

    @Test
    public void testItemsAreSortedInBlock() throws Exception {
        Interval interval1 = new Interval(0, 200, DateTimeZones.UTC);
        
        Interval overwrittenInterval = new Interval(100, 200, DateTimeZones.UTC);
        
        ItemAndBroadcast episodeAndBroadcast1 = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(0, 100, DateTimeZones.UTC));
        ItemAndBroadcast episodeAndBroadcast2 = itemAndBroadcast(2, METABROADCAST, channel, "two", overwrittenInterval);
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, interval1, ImmutableList.of(
                episodeAndBroadcast1, episodeAndBroadcast2
            ))
        );

        ItemAndBroadcast episodeAndBroadcast3 = itemAndBroadcast(3, METABROADCAST, channel, "two", overwrittenInterval);
        
        ScheduleUpdate scheduleUpdate = updater.updateBlocks(currentSchedule, ImmutableList.of(episodeAndBroadcast3), channel, overwrittenInterval);
        
        assertThat(scheduleUpdate.getUpdatedBlocks().get(0).getEntries().get(0), is(episodeAndBroadcast1));
        assertThat(scheduleUpdate.getUpdatedBlocks().get(0).getEntries().get(1), is(episodeAndBroadcast3));
        
        assertThat(scheduleUpdate.getStaleEntries(), hasItem(episodeAndBroadcast2));
    }

    @Test
    public void testItemWithUpdatedBroadcastTimeDoesntAppearTwice() throws Exception {
        Interval interval = new Interval(0, 200, DateTimeZones.UTC);
        
        ItemAndBroadcast iab1 = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(0, 100, DateTimeZones.UTC));
        ItemAndBroadcast iab2 = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(5, 105, DateTimeZones.UTC));
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, interval, ImmutableList.of(iab1))
        );
        
        ScheduleUpdate updatedSchedule = updater.updateBlocks(currentSchedule, ImmutableList.of(iab2), channel, interval);
        
        assertThat(updatedSchedule.getUpdatedBlocks().get(0).getEntries().size(), is(1));
        assertThat(updatedSchedule.getUpdatedBlocks().get(0).getEntries().get(0), is(iab2));

        assertTrue(updatedSchedule.getStaleEntries().isEmpty());
    }

    @Test
    public void testItemCanAppearMultipleTimesInASchedule() throws Exception {
        Interval interval1 = new Interval(0, 300, DateTimeZones.UTC);
        
        ItemAndBroadcast iab1 = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(0, 100, DateTimeZones.UTC));
        ItemAndBroadcast iab2 = itemAndBroadcast(1, METABROADCAST, channel, "two", new Interval(100, 200, DateTimeZones.UTC));
        ItemAndBroadcast iab3 = itemAndBroadcast(1, METABROADCAST, channel, "three", new Interval(200, 300, DateTimeZones.UTC));
        
        ItemAndBroadcast iab4 = itemAndBroadcast(2, METABROADCAST, channel, "four", new Interval(100,200, DateTimeZones.UTC));
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, interval1, ImmutableList.of(iab1, iab2, iab3))
        );
        
        Interval overwrittenInterval = new Interval(100, 300, DateTimeZones.UTC);
        
        List<ItemAndBroadcast> updateEntries = ImmutableList.of(iab4, iab3);

        ScheduleUpdate update = updater.updateBlocks(currentSchedule, updateEntries, channel, overwrittenInterval);
        
        ChannelSchedule block = update.getUpdatedBlocks().get(0);
        assertThat(block.getEntries().size(), is(3));
        assertThat(block.getEntries().get(0), is(iab1));
        assertThat(block.getEntries().get(1), is(iab4));
        assertThat(block.getEntries().get(2), is(iab3));
        
        assertThat(update.getStaleEntries(), hasItem(iab2));
    }
    
    @Test
    public void testStaleEntryOnlyAppearsOnceInStaleEntries() throws Exception {
        Interval interval = utcInterval(0, 200);
        
        ItemAndBroadcast episode1 = itemAndBroadcast(1, METABROADCAST, channel, "one", new Interval(0, 200, DateTimeZones.UTC));
        ItemAndBroadcast episode1Copy = new ItemAndBroadcast(
            episode1.getItem().copy(), 
            episode1.getBroadcast().copy()
        );
        
        List<ChannelSchedule> currentSchedule = ImmutableList.of(
            new ChannelSchedule(channel, utcInterval(0,100), ImmutableList.of(
                episode1
            )),
            new ChannelSchedule(channel, utcInterval(100,200), ImmutableList.of(
                episode1Copy
            ))
        );
        
        ItemAndBroadcast episode2 = itemAndBroadcast(2, METABROADCAST, channel, "one", new Interval(0, 200, DateTimeZones.UTC));
        List<ItemAndBroadcast> updateEntries = ImmutableList.of(episode2);
        
        ScheduleUpdate updatedSchedule = updater.updateBlocks(currentSchedule, updateEntries, channel, interval);
        
        assertThat(updatedSchedule.getUpdatedBlocks().get(0).getEntries().size(), is(1));
        assertThat(updatedSchedule.getUpdatedBlocks().get(0).getEntries().get(0), is(episode2));
        assertThat(updatedSchedule.getUpdatedBlocks().get(1).getEntries().size(), is(1));
        assertThat(updatedSchedule.getUpdatedBlocks().get(1).getEntries().get(0), is(episode2));
        
        assertThat(Iterables.getOnlyElement(updatedSchedule.getStaleEntries()), is(episode1));
    }

    private Interval utcInterval(int startInstant, int endInstant) {
        return new Interval(startInstant, endInstant, DateTimeZones.UTC);
    }
    
    private ItemAndBroadcast itemAndBroadcast(int id, Publisher source, Channel channel, String bId, Interval interval) {
        Episode episode = episode(id, source);
        Version version = new Version();
        episode.addVersion(version);
        Broadcast broadcast = broadcast(channel, bId, interval);
        version.addBroadcast(broadcast);
        return new ItemAndBroadcast(episode, broadcast);
    }

    private Broadcast broadcast(Channel channel, String bId, Interval interval) {
        Broadcast b = new Broadcast(channel.getCanonicalUri(), interval.getStart(), interval.getEnd());
        b.withId(bId);
        return b;
    }

    private Episode episode(int id, Publisher source) {
        Episode episode = new Episode();
        episode.setId(id);
        episode.setPublisher(source);
        return episode;
    }

}
