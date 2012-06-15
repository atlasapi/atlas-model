package org.atlasapi.persistence.lookup;

import java.util.Set;

import junit.framework.TestCase;

import org.atlasapi.media.content.Brand;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.Identified;
import org.atlasapi.media.content.Item;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.media.content.util.ContentCategory;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;
import org.atlasapi.persistence.lookup.entry.LookupRef;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class TransitiveLookupWriterTest extends TestCase {

    private final LookupEntryStore store = new InMemoryLookupEntryStore();
    private final TransitiveLookupWriter writer = new TransitiveLookupWriter(store);

    // Tests that trivial lookups are written reflexively for all content
    // identifiers
    public void testWriteNewLookup() {

        Item item = createItem("test", Publisher.BBC);

        writer.writeLookup(item, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.BBC));

        LookupEntry uriEntry = Iterables.getOnlyElement(store.entriesForUris(ImmutableList.of("testUri")));
        assertEquals(item.getCanonicalUri(), uriEntry.uri());
        assertEquals(item.getAliases(), uriEntry.aliases());
        assertEquals("testUri", Iterables.getOnlyElement(uriEntry.directEquivalents()).id());

        assertNotNull(uriEntry.created());
        assertNotNull(uriEntry.updated());

        assertEquals(item.getCanonicalUri(), Iterables.getOnlyElement(uriEntry.equivalents()).id());
        assertEquals(item.getPublisher(), Iterables.getOnlyElement(uriEntry.equivalents()).publisher());
        assertEquals(ContentCategory.TOP_LEVEL_ITEM, Iterables.getOnlyElement(uriEntry.equivalents()).category());

        LookupEntry aliasEntry = Iterables.getOnlyElement(store.entriesForUris(ImmutableList.of("testAlias")));
        assertEquals(Iterables.getOnlyElement(item.getAliases()), aliasEntry.uri());
        assertEquals(ImmutableSet.of(), aliasEntry.directEquivalents());

        assertEquals(item.getCanonicalUri(), Iterables.getOnlyElement(aliasEntry.equivalents()).id());
        assertEquals(item.getPublisher(), Iterables.getOnlyElement(aliasEntry.equivalents()).publisher());
        assertEquals(ContentCategory.TOP_LEVEL_ITEM, Iterables.getOnlyElement(aliasEntry.equivalents()).category());

        assertNotNull(aliasEntry.created());
        assertNotNull(aliasEntry.updated());

    }

    private Item createItem(String itemName, Publisher publisher) {
        Item item = new Item(itemName + "Uri", itemName + "Curie", Publisher.BBC);
        item.addAlias(itemName + "Alias");
        item.setPublisher(publisher);
        return item;
    }

    public void testWriteLookup() {

        Item paItem = createItem("test1", Publisher.PA);
        Item bbcItem = createItem("test2", Publisher.BBC);
        Item c4Item = createItem("test3", Publisher.C4);

        Set<Publisher> publishers = ImmutableSet.of(Publisher.PA, Publisher.BBC, Publisher.C4, Publisher.ITUNES);
        // Inserts reflexive entries for items PA, BBC, C4
        writer.writeLookup(paItem, ImmutableSet.<Content> of(), publishers);
        writer.writeLookup(bbcItem, ImmutableSet.<Content> of(), publishers);
        writer.writeLookup(c4Item, ImmutableSet.<Content> of(), publishers);

        // Make items BBC and C4 equivalent.
        writer.writeLookup(bbcItem, ImmutableSet.<Content> of(c4Item), publishers);

        hasEquivs(paItem, paItem);
        hasDirectEquivs(paItem, paItem);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make items PA and BBC equivalent, so all three are transitively
        // equivalent
        writer.writeLookup(paItem, ImmutableSet.<Content> of(bbcItem), publishers);

        hasEquivs(paItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(paItem, paItem, bbcItem);

        hasEquivs(bbcItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item, paItem);

        hasEquivs(c4Item, bbcItem, c4Item, paItem);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make item PA equivalent to nothing. Item PA just reflexive, item BBC and
        // C4 still equivalent.
        writer.writeLookup(paItem, ImmutableSet.<Content> of(), publishers);

        hasEquivs(paItem, paItem);
        hasDirectEquivs(paItem, paItem);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Make PA and BBC equivalent again.
        writer.writeLookup(paItem, ImmutableSet.<Content> of(bbcItem), publishers);

        hasEquivs(paItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(paItem, paItem, bbcItem);

        hasEquivs(bbcItem, bbcItem, c4Item, paItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item, paItem);

        hasEquivs(c4Item, bbcItem, c4Item, paItem);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        // Add a new item from Itunes.
        Item itunesItem = createItem("test4", Publisher.ITUNES);
        writer.writeLookup(itunesItem, ImmutableSet.<Content> of(), publishers);

        // Make PA equivalent to just Itunes, instead of BBC. PA and Itunes equivalent, BBC and
        // C4 equivalent.
        writer.writeLookup(paItem, ImmutableSet.<Content> of(itunesItem), publishers);

        hasEquivs(paItem, paItem, itunesItem);
        hasDirectEquivs(paItem, paItem, itunesItem);

        hasEquivs(bbcItem, bbcItem, c4Item);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, bbcItem, c4Item);
        hasDirectEquivs(c4Item, bbcItem, c4Item);

        hasEquivs(itunesItem, paItem, itunesItem);
        hasDirectEquivs(itunesItem, paItem, itunesItem);

        // Make all items equivalent.
        writer.writeLookup(paItem, ImmutableSet.<Content> of(c4Item, itunesItem), publishers);

        hasEquivs(paItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(paItem, paItem, c4Item, itunesItem);

        hasEquivs(bbcItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(bbcItem, bbcItem, c4Item);

        hasEquivs(c4Item, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(c4Item, paItem, bbcItem, c4Item);

        hasEquivs(itunesItem, paItem, bbcItem, c4Item, itunesItem);
        hasDirectEquivs(itunesItem, paItem, itunesItem);

    }

    private void hasEquivs(Content id, Content... transitiveEquivs) {
        LookupEntry entry = Iterables.getOnlyElement(store.entriesForUris(ImmutableList.of(id.getCanonicalUri())));
        assertEquals(ImmutableSet.copyOf(Iterables.transform(ImmutableSet.copyOf(transitiveEquivs),Identified.TO_URI)), ImmutableSet.copyOf(Iterables.transform(entry.equivalents(), LookupRef.TO_ID)));
    }

    private void hasDirectEquivs(Content id, Content... directEquivs) {
        LookupEntry entry = Iterables.getOnlyElement(store.entriesForUris(ImmutableList.of(id.getCanonicalUri())));
        assertEquals(ImmutableSet.copyOf(Iterables.transform(ImmutableSet.copyOf(directEquivs),Identified.TO_URI)), ImmutableSet.copyOf(Iterables.transform(entry.directEquivalents(), LookupRef.TO_ID)));
    }

    public void testBreakingEquivs() {
        
        Brand pivot = new Brand("pivot", "cpivot", Publisher.PA);
        Brand left = new Brand("left", "cleft", Publisher.PA);
        Brand right = new Brand("right", "cright", Publisher.PA);

        Set<Publisher> publishers = ImmutableSet.of(Publisher.PA);
        writer.writeLookup(pivot, ImmutableSet.of(left,right), publishers);
        writer.writeLookup(left, ImmutableSet.of(right), publishers);
        
        writer.writeLookup(pivot, ImmutableSet.of(left), publishers);
        writer.writeLookup(left, ImmutableSet.<Content>of(), publishers);
        
        hasEquivs(pivot, pivot);
        
    }
    
    public void testDoesntWriteEquivalentsForContentOfIgnoredPublishers() {
        
        Item paItem = createItem("paItem",Publisher.PA);
        Item c4Item = createItem("c4Item",Publisher.C4);
        
        writer.writeLookup(paItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PA));
        writer.writeLookup(c4Item, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.C4));

        hasEquivs(paItem, paItem);
        hasEquivs(c4Item, c4Item);
        
        writer.writeLookup(paItem, ImmutableSet.of(c4Item), ImmutableSet.of(Publisher.PA, Publisher.BBC));
        
        hasEquivs(paItem, paItem);
        hasEquivs(c4Item, c4Item);
        hasEquivs(c4Item, c4Item);
        
    }
    
    public void testDoesntBreakEquivalenceForContentOfIgnoredPublishers() {
        
        Item paItem = createItem("paItem1",Publisher.PA);
        Item c4Item = createItem("c4Item1",Publisher.C4);
        Item bbcItem = createItem("bbcItem1",Publisher.BBC);
        Item fiveItem = createItem("fiveItem1", Publisher.FIVE);
        
        writer.writeLookup(paItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PA));
        writer.writeLookup(c4Item, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.C4));
        writer.writeLookup(bbcItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.BBC));
        writer.writeLookup(fiveItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.FIVE));
        
        //Make PA and BBC equivalent
        writer.writeLookup(paItem, ImmutableSet.of(bbcItem), ImmutableSet.of(Publisher.PA, Publisher.BBC));

        hasEquivs(paItem, paItem, bbcItem);
        hasDirectEquivs(paItem, paItem, bbcItem);
        
        hasEquivs(bbcItem, bbcItem, paItem);
        hasDirectEquivs(bbcItem, bbcItem, paItem);
        
        hasEquivs(c4Item, c4Item);
        hasDirectEquivs(c4Item, c4Item);
        
        hasEquivs(fiveItem, fiveItem);
        hasDirectEquivs(fiveItem, fiveItem);

        //Make PA and C4 equivalent, ignoring BBC content. PA, BBC, C4 all equivalent.
        writer.writeLookup(paItem, ImmutableSet.of(c4Item), ImmutableSet.of(Publisher.PA, Publisher.C4));

        hasEquivs(paItem, paItem, bbcItem, c4Item);
        hasDirectEquivs(paItem, paItem, bbcItem, c4Item);
        
        hasEquivs(bbcItem, bbcItem, paItem, c4Item);
        hasDirectEquivs(bbcItem, paItem, bbcItem);
        
        hasEquivs(c4Item, c4Item, paItem, bbcItem);
        hasDirectEquivs(c4Item, paItem, c4Item);
        
        hasEquivs(fiveItem, fiveItem);
        hasDirectEquivs(fiveItem, fiveItem);
        
        //Make PA and 5 equivalent, including C4 content. PA, BBC, 5 all equivalent. 
        writer.writeLookup(paItem, ImmutableSet.of(fiveItem), ImmutableSet.of(Publisher.PA, Publisher.C4, Publisher.FIVE));
        
        hasEquivs(paItem, paItem, bbcItem, fiveItem);
        hasDirectEquivs(paItem, paItem, bbcItem, fiveItem);
        
        hasEquivs(bbcItem, bbcItem, paItem, fiveItem);
        hasDirectEquivs(bbcItem, paItem, bbcItem);
        
        hasEquivs(c4Item, c4Item);
        hasDirectEquivs(c4Item, c4Item);
        
        hasEquivs(fiveItem, fiveItem, bbcItem, paItem);
        hasDirectEquivs(fiveItem, fiveItem, paItem);
        
    }
    
    public void testDoesntBreakEquivalenceForContentOfIgnoredPublishersWhenLinkingItemIsNotSubject() {
        
        Item paItem = createItem("paItem2",Publisher.PA);
        Item pnItem = createItem("pnItem2",Publisher.PREVIEW_NETWORKS);
        Item bbcItem = createItem("bbcItem2",Publisher.BBC);
    
        writer.writeLookup(paItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PA));
        writer.writeLookup(pnItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.PREVIEW_NETWORKS));
        writer.writeLookup(bbcItem, ImmutableSet.<Content> of(), ImmutableSet.of(Publisher.BBC));

        //Make PA and BBC equivalent
        writer.writeLookup(paItem, ImmutableSet.of(bbcItem), ImmutableSet.of(Publisher.PA, Publisher.BBC));
        
        writer.writeLookup(pnItem, ImmutableSet.of(paItem), ImmutableSet.of(Publisher.PREVIEW_NETWORKS, Publisher.PA));
        
        hasEquivs(paItem, paItem, bbcItem, pnItem);
        hasDirectEquivs(paItem, paItem, bbcItem, pnItem);
        
        hasEquivs(bbcItem, bbcItem, paItem, pnItem);
        hasDirectEquivs(bbcItem, paItem, bbcItem);
        
        hasEquivs(pnItem, pnItem, paItem, bbcItem);
        hasDirectEquivs(pnItem, paItem, pnItem);
        
    }
    
}
