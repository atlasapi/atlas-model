package org.atlasapi.media.content.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.atlasapi.media.content.ContentWriter;
import org.atlasapi.media.content.Item;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.persistence.lookup.InMemoryLookupEntryStore;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class EquivalenceWritingContentWriterTest {

    private final LookupEntryStore lookupEntryStore = new InMemoryLookupEntryStore();
    private final ContentWriter delegate = mock(ContentWriter.class);
    
    private final EquivalenceWritingContentWriter contentWriter = new EquivalenceWritingContentWriter(delegate, lookupEntryStore);

    @Test
    public void testWritingContentWithEquivalencesWritesEquivalences() {

        Item firstSubjectItem = new Item("subjectUri", "curie", Publisher.METABROADCAST);
        LookupEntry subject = LookupEntry.lookupEntryFrom(firstSubjectItem);
        lookupEntryStore.store(subject);

        Item generatedEquivalent = new Item("generatedUri", "curie", Publisher.FIVE);
        LookupEntry generatedEquiv = LookupEntry.lookupEntryFrom(generatedEquivalent);
        
        Item explicitEquivalent = new Item("equivUri", "durie", Publisher.BBC);
        LookupEntry explicitEquiv = LookupEntry.lookupEntryFrom(explicitEquivalent)
                .copyWithDirectEquivalents(ImmutableList.of(generatedEquiv.lookupRef()))
                .copyWithEquivalents(ImmutableList.of(generatedEquiv.lookupRef()));
        lookupEntryStore.store(explicitEquiv);

        generatedEquiv = generatedEquiv
                .copyWithDirectEquivalents(ImmutableList.of(explicitEquiv.lookupRef()))
                .copyWithExplicitEquivalents(ImmutableList.of(explicitEquiv.lookupRef()));
        lookupEntryStore.store(generatedEquiv);
        
        firstSubjectItem.setEquivalentTo(ImmutableSet.of(explicitEquiv.lookupRef()));
        
        contentWriter.createOrUpdate(firstSubjectItem);

        verify(delegate).createOrUpdate(firstSubjectItem);
        
        LookupEntry first = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(firstSubjectItem.getCanonicalUri())));
        assertTrue(first.explicitEquivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(first.equivalents().contains(explicitEquiv.lookupRef()));

        LookupEntry explicit = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(explicitEquivalent.getCanonicalUri())));
        assertTrue(explicit.explicitEquivalents().contains(subject.lookupRef()));
        assertTrue(explicit.directEquivalents().contains(generatedEquiv.lookupRef()));
        assertTrue(explicit.equivalents().contains(subject.lookupRef()));
        assertTrue(explicit.equivalents().contains(generatedEquiv.lookupRef()));

        LookupEntry generated = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(generatedEquivalent.getCanonicalUri())));
        assertTrue(generated.directEquivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(generated.equivalents().contains(subject.lookupRef()));
        assertTrue(generated.equivalents().contains(explicitEquiv.lookupRef()));
        
        
        Item secondSubjectItem = new Item("secondUri", "eurie", Publisher.C4);
        LookupEntry secondSubject = LookupEntry.lookupEntryFrom(secondSubjectItem);
        lookupEntryStore.store(secondSubject);
        
        secondSubjectItem.setEquivalentTo(ImmutableSet.of(explicitEquiv.lookupRef()));
        
        contentWriter.createOrUpdate(secondSubjectItem);

        verify(delegate).createOrUpdate(secondSubjectItem);
        
        LookupEntry second = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(secondSubjectItem.getCanonicalUri())));
        assertTrue(second.explicitEquivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(second.equivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(second.equivalents().contains(subject.lookupRef()));
        assertTrue(second.equivalents().contains(generatedEquiv.lookupRef()));
        
        first = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(firstSubjectItem.getCanonicalUri())));
        assertTrue(first.explicitEquivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(first.equivalents().contains(explicitEquiv.lookupRef()));
        assertTrue(first.equivalents().contains(secondSubject.lookupRef()));
        assertTrue(first.equivalents().contains(generatedEquiv.lookupRef()));
        
        explicit = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(explicitEquivalent.getCanonicalUri())));
        assertTrue(explicit.explicitEquivalents().contains(subject.lookupRef()));
        assertTrue(explicit.explicitEquivalents().contains(secondSubject.lookupRef()));
        assertTrue(explicit.equivalents().contains(subject.lookupRef()));
        assertTrue(explicit.equivalents().contains(secondSubject.lookupRef()));
        assertTrue(explicit.equivalents().contains(generatedEquiv.lookupRef()));

        generated = Iterables.getOnlyElement(lookupEntryStore.entriesForUris(ImmutableSet.of(generatedEquivalent.getCanonicalUri())));
        assertTrue(explicit.equivalents().contains(subject.lookupRef()));
        assertTrue(explicit.equivalents().contains(secondSubject.lookupRef()));
        assertTrue(explicit.equivalents().contains(explicitEquiv.lookupRef()));
    }

}
