package org.atlasapi.equiv;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.PatternLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.atlasapi.equiv.ContentRef;
import org.atlasapi.equiv.EquivalenceRecord;
import org.atlasapi.equiv.EquivalenceRecordStore;
import org.atlasapi.equiv.EquivalenceRecordWriter;
import org.atlasapi.equiv.EquivalenceRef;
import org.atlasapi.equiv.TransitiveEquivalenceRecordWriter;
import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Identifiables;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.metabroadcast.common.collect.ImmutableOptionalMap;
import com.metabroadcast.common.collect.OptionalMap;

@RunWith(MockitoJUnitRunner.class)
public class TransitiveEquivalenceRecordWriterTest {

    private final EquivalenceRecordStore store = new InMemoryRecordStore();
    private final EquivalenceRecordWriter writer = TransitiveEquivalenceRecordWriter.generated(store);

    @BeforeClass
    public static void before() throws Exception {
        org.apache.log4j.Logger root = org.apache.log4j.Logger.getRootLogger();
        root.addAppender(new ConsoleAppender(
            new PatternLayout(PatternLayout.TTCC_CONVERSION_PATTERN)));
        root.setLevel(Level.WARN);
    }
    
    private Item createItem(String itemName, Publisher publisher) {
        Item item = new Item(itemName + "Uri", itemName + "Curie", Publisher.BBC);
        item.setId(Id.valueOf(itemName.hashCode()));
        item.setPublisher(publisher);
        return item;
    }
    
    private class InMemoryRecordStore implements EquivalenceRecordStore {

        private final Logger log = LoggerFactory.getLogger(getClass()); 
        
        Map<Id, EquivalenceRecord> map = Maps.newHashMap();
        
        @Override
        public void writeRecords(Iterable<EquivalenceRecord> records) {
            for (EquivalenceRecord record : records) {
                map.put(record.getId(), record);
            }
        }

        @Override
        public OptionalMap<Id, EquivalenceRecord> resolveRecords(Iterable<Id> ids) {
            log.trace("READ records {}", ids);
            return ImmutableOptionalMap.fromMap(Maps.filterKeys(map,
                    Predicates.in(ImmutableSet.copyOf(ids))));
        }
        
    }

    @Test
    public void testWriteLookup() {

        Item paItem = createItem("test1", Publisher.PA);
        Item bbcItem = createItem("test2", Publisher.BBC);
        Item c4Item = createItem("test3", Publisher.C4);

        Set<Publisher> publishers = ImmutableSet.of(Publisher.PA,
                Publisher.BBC, Publisher.C4, Publisher.ITUNES);
        
        // Inserts reflexive entries for items PA, BBC, C4
//        store.writeRecord(EquivalenceRecord.valueOf(paItem));
//        store.writeRecord(EquivalenceRecord.valueOf(bbcItem));
//        store.writeRecord(EquivalenceRecord.valueOf(c4Item));

        // Make items BBC and C4 equivalent.
        writeLookup(bbcItem, ImmutableSet.of(c4Item), publishers);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make items PA and BBC equivalent, so all three are transitively
        // equivalent
        writeLookup(paItem, ImmutableSet.of(bbcItem), publishers);

        hasEquivs(paItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(paItem, paItem, bbcItem);

        hasEquivs(bbcItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item, paItem);

        hasEquivs(c4Item, bbcItem, c4Item, paItem);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make item PA equivalent to nothing. Item PA just reflexive, item BBC
        // and
        // C4 still equivalent.
        writeLookup(paItem, ImmutableSet.<Content> of(), publishers);

        hasEquivs(paItem, paItem);
        hasDirectEquivs(paItem, paItem);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make PA and BBC equivalent again.
        writeLookup(paItem, ImmutableSet.of(bbcItem), publishers);

        hasEquivs(paItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(paItem, paItem, bbcItem);

        hasEquivs(bbcItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item, paItem);

        hasEquivs(c4Item, bbcItem, c4Item, paItem);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Add a new item from Itunes.
        Item itunesItem = createItem("test4", Publisher.ITUNES);
        store.writeRecords(ImmutableList.of(EquivalenceRecord.valueOf(itunesItem)));

        // Make PA equivalent to just Itunes, instead of BBC. PA and Itunes
        // equivalent, BBC and
        // C4 equivalent.
        writeLookup(paItem, ImmutableSet.of(itunesItem), publishers);

        hasEquivs(paItem, paItem, itunesItem);
        hasDirectEquivs(paItem, paItem, itunesItem);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        hasEquivs(itunesItem, paItem, itunesItem);
        hasDirectEquivs(itunesItem, paItem, itunesItem);

        // Make all items equivalent.
        writeLookup(paItem, ImmutableSet.of(c4Item, itunesItem), publishers);

        hasEquivs(paItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(paItem, paItem, c4Item, itunesItem);

        hasEquivs(bbcItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(c4Item, paItem, bbcItem, c4Item);

        hasEquivs(itunesItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(itunesItem, paItem, itunesItem);

    }

    protected void writeLookup(Content subject, ImmutableSet<? extends Content> equivs,
            Set<Publisher> publishers) {
        writer.writeLookup(ContentRef.valueOf(subject), Iterables.transform(equivs,
                new Function<Content, ContentRef>() {

                    @Override
                    public ContentRef apply(@Nullable Content input) {
                        return ContentRef.valueOf(input);
                    }
                }), publishers);
    }

    private void hasEquivs(Content id, Content... transitiveEquivs) {
        EquivalenceRecord entry = store.resolveRecords(ImmutableList.of(id.getId())).get(id.getId()).get();
        assertEquals(
                ImmutableSet.copyOf(Iterables.transform(ImmutableSet.copyOf(transitiveEquivs),
                        Identifiables.toId())),
                ImmutableSet.copyOf(Iterables.transform(entry.getEquivalents(), Identifiables.toId())));
    }

    private void hasDirectEquivs(Content id, Content... directEquivs) {
        EquivalenceRecord entry = store.resolveRecords(ImmutableList.of(id.getId())).get(id.getId()).get();
        assertEquals(
                ImmutableSet.copyOf(Iterables.transform(ImmutableSet.copyOf(directEquivs),
                        Identifiables.toId())),
                ImmutableSet.copyOf(Iterables.transform(entry.getGeneratedAdjacents(), Identifiables.toId())));
    }

    @Test
    public void testBreakingEquivs() {

        Brand pivot = new Brand("pivot", "cpivot", Publisher.PA);
        pivot.setId(Id.valueOf(1));
        Brand left = new Brand("left", "cleft", Publisher.PA);
        left.setId(Id.valueOf(2));
        Brand right = new Brand("right", "cright", Publisher.PA);
        right.setId(Id.valueOf(3));

        Set<Publisher> publishers = ImmutableSet.of(Publisher.PA);
        writeLookup(pivot, ImmutableSet.of(left, right), publishers);
        writeLookup(left, ImmutableSet.of(right), publishers);

        writeLookup(pivot, ImmutableSet.of(left), publishers);
        writeLookup(left, ImmutableSet.<Content> of(), publishers);

        hasEquivs(pivot, pivot);

    }

    @Test
    public void testDoesntWriteEquivalentsForContentOfIgnoredPublishers() {

        Item paItem = createItem("paItem", Publisher.PA);
        Item c4Item = createItem("c4Item", Publisher.C4);

        store.writeRecords(ImmutableList.of(
            EquivalenceRecord.valueOf(paItem), EquivalenceRecord.valueOf(c4Item)
        ));
        
        writeLookup(paItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PA));
        writeLookup(c4Item, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.C4));

        hasEquivs(paItem, paItem);
        hasEquivs(c4Item, c4Item);

        writeLookup(paItem, ImmutableSet.of(c4Item), ImmutableSet.of(Publisher.PA, Publisher.BBC));

        hasEquivs(paItem, paItem);
        hasEquivs(c4Item, c4Item);

    }

    @Test
    public void testDoesntBreakEquivalenceForContentOfIgnoredPublishers() {

        Item paItem = createItem("paItem1", Publisher.PA);
        Item c4Item = createItem("c4Item1", Publisher.C4);
        Item bbcItem = createItem("bbcItem1", Publisher.BBC);
        Item fiveItem = createItem("fiveItem1", Publisher.FIVE);

        writeLookup(paItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PA));
        writeLookup(c4Item, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.C4));
        writeLookup(bbcItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.BBC));
        writeLookup(fiveItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.FIVE));

        // Make PA and BBC equivalent
        writeLookup(paItem, ImmutableSet.of(bbcItem), ImmutableSet.of(Publisher.PA, Publisher.BBC));

        hasEquivs(paItem, paItem, bbcItem);
        hasDirectEquivs(paItem, paItem, bbcItem);

        hasEquivs(bbcItem, bbcItem, paItem);
        hasDirectEquivs(bbcItem, bbcItem, paItem);

        // Make PA and C4 equivalent, ignoring BBC content. PA, BBC, C4 all
        // equivalent.
        writeLookup(paItem, ImmutableSet.of(c4Item), ImmutableSet.of(Publisher.PA, Publisher.C4));

        hasEquivs(paItem, paItem, bbcItem, c4Item);
        hasDirectEquivs(paItem, paItem, bbcItem, c4Item);

        hasEquivs(bbcItem, bbcItem, paItem, c4Item);
        hasDirectEquivs(bbcItem, paItem, bbcItem);

        hasEquivs(c4Item, c4Item, paItem, bbcItem);
        hasDirectEquivs(c4Item, paItem, c4Item);

        // Make PA and 5 equivalent, including C4 content. PA, BBC, 5 all
        // equivalent.
        writeLookup(paItem, ImmutableSet.of(fiveItem), ImmutableSet.of(Publisher.PA,
                Publisher.C4,
                Publisher.FIVE));

        hasEquivs(paItem, paItem, bbcItem, fiveItem);
        hasDirectEquivs(paItem, paItem, bbcItem, fiveItem);

        hasEquivs(bbcItem, bbcItem, paItem, fiveItem);
        hasDirectEquivs(bbcItem, paItem, bbcItem);

        hasEquivs(c4Item, c4Item);
        hasDirectEquivs(c4Item, c4Item);

        hasEquivs(fiveItem, fiveItem, bbcItem, paItem);
        hasDirectEquivs(fiveItem, fiveItem, paItem);

    }

    @Test
    public void testDoesntBreakEquivalenceForContentOfIgnoredPublishersWhenLinkingItemIsNotSubject() {

        Item paItem = createItem("paItem2", Publisher.PA);
        Item pnItem = createItem("pnItem2", Publisher.PREVIEW_NETWORKS);
        Item bbcItem = createItem("bbcItem2", Publisher.BBC);

        // Make PA and BBC equivalent
        writeLookup(paItem, ImmutableSet.of(bbcItem), ImmutableSet.of(Publisher.PA, Publisher.BBC));

        writeLookup(pnItem, ImmutableSet.of(paItem), ImmutableSet.of(Publisher.PREVIEW_NETWORKS,
                Publisher.PA));

        hasEquivs(paItem, paItem, bbcItem, pnItem);
        hasDirectEquivs(paItem, paItem, bbcItem, pnItem);

        hasEquivs(bbcItem, bbcItem, paItem, pnItem);
        hasDirectEquivs(bbcItem, paItem, bbcItem);

        hasEquivs(pnItem, pnItem, paItem, bbcItem);
        hasDirectEquivs(pnItem, paItem, pnItem);

    }

    @Test
    public void testDoesntWriteEquivalencesWhenEquivalentsDontChange() {

        EquivalenceRecordStore store = mock(EquivalenceRecordStore.class);
        EquivalenceRecordWriter writer = TransitiveEquivalenceRecordWriter.generated(store);
        
        Item paItem = createItem("paItem2", Publisher.PA);
        Item pnItem = createItem("pnItem2", Publisher.PREVIEW_NETWORKS);

        EquivalenceRecord paLookupEntry = EquivalenceRecord.valueOf(paItem)
            .copyWithGeneratedAdjacent(ImmutableList.of(EquivalenceRef.valueOf(pnItem)));
        when(store.resolveRecords(ImmutableList.of(paItem.getId())))
            .thenReturn(ImmutableOptionalMap.fromMap(ImmutableMap.of(paLookupEntry.getId(), paLookupEntry)));

        writer.writeLookup(ContentRef.valueOf(paItem),
                ImmutableSet.of(ContentRef.valueOf(pnItem)),
                ImmutableSet.of(Publisher.PA, Publisher.PREVIEW_NETWORKS));

        verify(store, never()).writeRecords(Mockito.<Iterable<EquivalenceRecord>>any());
    }

}
