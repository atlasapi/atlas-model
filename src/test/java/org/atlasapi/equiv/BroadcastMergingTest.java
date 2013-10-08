package org.atlasapi.equiv;

import static com.metabroadcast.common.time.DateTimeZones.UTC;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.atlasapi.application.ApplicationSources;
import org.atlasapi.application.SourceReadEntry;
import org.atlasapi.application.SourceStatus;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class BroadcastMergingTest {

    private final OutputContentMerger executor = new OutputContentMerger();
    private final ApplicationSources sources = ApplicationSources.DEFAULT_SOURCES
            .copy().withPrecedence(true)
            .withReads(ImmutableList.of(
                    new SourceReadEntry(Publisher.BBC, SourceStatus.AVAILABLE_ENABLED),
                    new SourceReadEntry(Publisher.FACEBOOK, SourceStatus.AVAILABLE_ENABLED)
             ))
            .build();
    
    @Test
    public void testBroadcastMergingNoBroadcasts() {
        Item chosenItem = new Item();
        chosenItem.setId(1);
        chosenItem.setPublisher(Publisher.BBC);
        chosenItem.setCanonicalUri("chosenItem");
        Version emptyVersion = new Version();
        chosenItem.addVersion(emptyVersion);

        Item notChosenItem = new Item();
        notChosenItem.setId(2);
        notChosenItem.setPublisher(Publisher.FACEBOOK);
        notChosenItem.setCanonicalUri("notChosenItem");
        Version version = new Version();
        version.addBroadcast(new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC)));
        notChosenItem.addVersion(version);
        
        chosenItem.addEquivalentTo(notChosenItem);
        notChosenItem.addEquivalentTo(chosenItem);
        
        executor.merge(sources, ImmutableList.of(chosenItem, notChosenItem));
        
        assertTrue(emptyVersion.getBroadcasts().isEmpty());
    }
    
    @Test
    public void testBroadcastMergingNonMatchingBroadcasts() {
        Item chosenItem = new Item();
        chosenItem.setId(1);
        chosenItem.setPublisher(Publisher.BBC);
        chosenItem.setCanonicalUri("chosenItem");
        Version chosenVersion = new Version();
        chosenVersion.addBroadcast(new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC)));
        chosenItem.addVersion(chosenVersion);

        Item notChosenItem = new Item();
        notChosenItem.setId(2);
        notChosenItem.setPublisher(Publisher.FACEBOOK);
        notChosenItem.setCanonicalUri("notChosenItem");
        Version version = new Version();
        // different broadcast channel
        version.addBroadcast(new Broadcast("www.bbc.co.uk/services/bbcone", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC)));
        // different start time
        version.addBroadcast(new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,4,0,0,0,UTC), new DateTime(2012,1,4,0,0,0,UTC)));
        notChosenItem.addVersion(version);
        
        chosenItem.addEquivalentTo(notChosenItem);
        notChosenItem.addEquivalentTo(chosenItem);
        
        executor.merge(sources, ImmutableList.of(chosenItem, notChosenItem));
        
        assertTrue(chosenVersion.getBroadcasts().size() == 1);
    }
    
    @Test
    public void testBroadcastMergingMatchingBroadcasts() {
        Item chosenItem = new Item();
        chosenItem.setId(1);
        chosenItem.setPublisher(Publisher.BBC);
        chosenItem.setCanonicalUri("chosenItem");
        Version chosenVersion = new Version();
        Broadcast chosenBroadcast = new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC));
        chosenBroadcast.addAliasUrl("chosenBroadcast");
        chosenBroadcast.addAlias(new Alias("chosenNamspace", "chosenValue"));
        chosenBroadcast.setSubtitled(true);
        chosenVersion.addBroadcast(chosenBroadcast);
        chosenItem.addVersion(chosenVersion);

        Item notChosenItem = new Item();
        notChosenItem.setId(2);
        notChosenItem.setCanonicalUri("notChosenItem");
        notChosenItem.setPublisher(Publisher.FACEBOOK);
        Version version = new Version();
        Broadcast broadcast = new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC));
        broadcast.addAliasUrl("non-chosen alias");
        broadcast.addAlias(new Alias("notChosenNamespace", "notChosenValue"));
        broadcast.setAudioDescribed(true);
        broadcast.setHighDefinition(false);
        broadcast.setSurround(false);
        broadcast.setSubtitled(false);
        version.addBroadcast(broadcast);
        notChosenItem.addVersion(version);
        
        chosenItem.addEquivalentTo(notChosenItem);
        notChosenItem.addEquivalentTo(chosenItem);
        
        executor.merge(sources, ImmutableList.of(chosenItem, notChosenItem));
        
        // ensure that the broadcast matched, 
        // and the fields on the non-chosen broadcast 
        // are merged only when the original broadcast's fields are null
        Broadcast mergedBroadcast = Iterables.getOnlyElement(chosenVersion.getBroadcasts());
        assertTrue(mergedBroadcast.getAudioDescribed());
        assertFalse(mergedBroadcast.getHighDefinition());
        assertFalse(mergedBroadcast.getSurround());
        assertTrue(mergedBroadcast.getSubtitled());
        assertTrue(mergedBroadcast.getAliases().size() == 2);
    }
    
    @Test
    public void testBroadcastMergingMatchingBroadcastsWithPrecedence() {
        Item chosenItem = new Item();
        chosenItem.setId(1);
        chosenItem.setCanonicalUri("chosenItem");
        chosenItem.setPublisher(Publisher.BBC);
        Version chosenVersion = new Version();
        Broadcast chosenBroadcast = new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC));
        chosenBroadcast.addAliasUrl("chosenBroadcast");
        chosenBroadcast.addAlias(new Alias("chosenNamspace", "chosenValue"));
        chosenBroadcast.setSubtitled(true);
        chosenVersion.addBroadcast(chosenBroadcast);
        chosenItem.addVersion(chosenVersion);

        Item notChosenBbcItem = new Item();
        notChosenBbcItem.setId(2);
        notChosenBbcItem.setCanonicalUri("notChosenItem");
        notChosenBbcItem.setPublisher(Publisher.BBC);
        Version version = new Version();
        Broadcast broadcast = new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC));
        broadcast.addAliasUrl("non-chosen alias");
        broadcast.addAlias(new Alias("notChosenNamespace", "notChosenValue"));
        broadcast.setAudioDescribed(true);
        broadcast.setHighDefinition(true);
        broadcast.setSubtitled(false);
        version.addBroadcast(broadcast);
        notChosenBbcItem.addVersion(version);
        
        Item notChosenFbItem = new Item();
        notChosenFbItem.setId(2);
        notChosenFbItem.setCanonicalUri("notChosenItem");
        notChosenFbItem.setPublisher(Publisher.FACEBOOK);
        version = new Version();
        broadcast = new Broadcast("www.bbc.co.uk/services/bbctwo", new DateTime(2012,1,1,0,0,0,UTC), new DateTime(2012,1,1,0,0,0,UTC));
        broadcast.addAliasUrl("non-chosen alias");
        broadcast.addAlias(new Alias("notChosenFBNamespace", "notChosenFBValue"));
        broadcast.setAudioDescribed(true);
        broadcast.setHighDefinition(false);
        broadcast.setSurround(false);
        broadcast.setSubtitled(false);
        version.addBroadcast(broadcast);
        notChosenFbItem.addVersion(version);
        
        chosenItem.addEquivalentTo(notChosenBbcItem);
        chosenItem.addEquivalentTo(notChosenFbItem);
        notChosenBbcItem.addEquivalentTo(chosenItem);
        notChosenBbcItem.addEquivalentTo(notChosenFbItem);
        notChosenFbItem.addEquivalentTo(chosenItem);
        notChosenFbItem.addEquivalentTo(notChosenBbcItem);

        executor.merge(sources, ImmutableList.of(chosenItem, notChosenBbcItem, notChosenFbItem));
        
        // ensure that the broadcast matched, 
        // and the fields on the non-chosen broadcast 
        // are merged only when the original broadcast's fields are null
        // and that the most precedent broadcast's values are used
        Broadcast mergedBroadcast = Iterables.getOnlyElement(chosenVersion.getBroadcasts());
        assertTrue(mergedBroadcast.getAudioDescribed());
        assertTrue(mergedBroadcast.getHighDefinition());
        assertFalse(mergedBroadcast.getSurround());
        assertTrue(mergedBroadcast.getSubtitled());
        assertTrue(mergedBroadcast.getAliases().size() == 3);
    }
}
