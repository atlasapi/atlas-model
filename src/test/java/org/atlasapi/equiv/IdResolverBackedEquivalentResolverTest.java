package org.atlasapi.equiv;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.IdResolver;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Resolved;
import org.atlasapi.output.Annotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.Futures;
import com.metabroadcast.common.collect.ImmutableOptionalMap;

@RunWith(MockitoJUnitRunner.class)
public class IdResolverBackedEquivalentResolverTest {

    @SuppressWarnings("unchecked")
    private IdResolver<Content> resolver = mock(IdResolver.class);
    private EquivalenceRecordStore store = mock(EquivalenceRecordStore.class);
    private final EquivalentResolver<Content> equivResolver = 
        IdResolverBackedEquivalentResolver.valueOf(store, resolver);
    
    @Test
    public void testResolvesEquivalentContent() throws Exception {
        
        Content one = new Episode(Id.valueOf(1), Publisher.BBC);
        Content two = new Episode(Id.valueOf(2), Publisher.PA);
        
        EquivalenceRef refOne = EquivalenceRef.valueOf(one);
        EquivalenceRef refTwo = EquivalenceRef.valueOf(two);
        
        EquivalenceRecord recOne = EquivalenceRecord.valueOf(one)
            .copyWithEquivalents(ImmutableList.of(refOne, refTwo));
        
        when(store.resolveRecords(argThat(hasItems(one.getId()))))
            .thenReturn(ImmutableOptionalMap.of(one.getId(), recOne));
        
        when(resolver.resolveIds(argThat(hasItems(one.getId(), two.getId()))))
            .thenReturn(Futures.immediateFuture(Resolved.valueOf(ImmutableList.of(one, two))));
        
        ResolvedEquivalents<Content> resolved = equivResolver.resolveIds(ImmutableList.of(one.getId()),
            ImmutableSet.of(Publisher.BBC, Publisher.PA), Annotation.defaultAnnotations()).get();
        
        assertThat(Iterables.getOnlyElement(resolved.keySet()), is(one.getId()));
        List<Content> equivs = ImmutableList.copyOf(resolved.get(one.getId()));
        assertThat(equivs.size(), is(2));
        assertThat(equivs.get(0).getEquivalentTo(), hasItem(EquivalenceRef.valueOf(equivs.get(1))));
        assertThat(equivs.get(1).getEquivalentTo(), hasItem(EquivalenceRef.valueOf(equivs.get(0))));
    }
    
    @Test
    public void testWhenIdNotResolvedTheresNoEntryForId() throws Exception {
        
        Id one = Id.valueOf(1);
        when(store.resolveRecords(argThat(hasItems(one))))
            .thenReturn(ImmutableOptionalMap.<Id,EquivalenceRecord>of());
        
        when(resolver.resolveIds(argThat(hasItems(one))))
            .thenReturn(Futures.immediateFuture(Resolved.<Content>empty()));
        
        ResolvedEquivalents<Content> resolved = equivResolver.resolveIds(ImmutableList.of(one),
                ImmutableSet.of(Publisher.BBC), Annotation.defaultAnnotations()).get();
        
        assertTrue(resolved.isEmpty());
        
        verify(resolver).resolveIds(argThat(hasItems(one)));
    }
    
    @Test
    public void testResolvesIdWhenNoEquivalenceRecordForId() throws Exception {

        Content one = new Episode(Id.valueOf(1), Publisher.BBC);
        
        when(store.resolveRecords(argThat(hasItems(one.getId()))))
            .thenReturn(ImmutableOptionalMap.<Id,EquivalenceRecord>of());
        
        when(resolver.resolveIds(argThat(hasItems(one.getId()))))
            .thenReturn(Futures.immediateFuture(Resolved.valueOf(ImmutableList.of(one))));
        
        ResolvedEquivalents<Content> resolved = equivResolver.resolveIds(ImmutableList.of(one.getId()),
                ImmutableSet.of(Publisher.BBC), Annotation.defaultAnnotations()).get();
        
        assertThat(ImmutableSet.copyOf(resolved.get(one.getId())), is(ImmutableSet.of(one)));
        
        verify(resolver).resolveIds(argThat(hasItems(one.getId())));
        
    }

    @Test
    public void testDoesntResolveIdFromOtherPublisher() throws Exception {

        Content one = new Episode(Id.valueOf(1), Publisher.BBC);
        Content two = new Episode(Id.valueOf(2), Publisher.PA);
        
        EquivalenceRef refOne = EquivalenceRef.valueOf(one);
        EquivalenceRef refTwo = EquivalenceRef.valueOf(two);
        
        EquivalenceRecord recOne = EquivalenceRecord.valueOf(one)
            .copyWithEquivalents(ImmutableList.of(refOne, refTwo));
        
        when(store.resolveRecords(argThat(hasItems(one.getId()))))
            .thenReturn(ImmutableOptionalMap.of(one.getId(), recOne));
        
        when(resolver.resolveIds(argThat(hasItems(one.getId()))))
            .thenReturn(Futures.immediateFuture(Resolved.valueOf(ImmutableList.of(one))));
        
        ResolvedEquivalents<Content> resolved = equivResolver.resolveIds(ImmutableList.of(one.getId()),
                ImmutableSet.of(Publisher.BBC), Annotation.defaultAnnotations()).get();
        
        assertThat(ImmutableSet.copyOf(resolved.get(one.getId())), is(ImmutableSet.of(one)));
        
        verify(resolver).resolveIds(argThat(hasItems(one.getId())));
        verify(resolver, never()).resolveIds(argThat(hasItems(two.getId())));
    }
    
    @Test
    public void testDoesntReturnEntryForOtherPublisherWithMissingRecord() throws Exception {

        Content one = new Episode(Id.valueOf(1), Publisher.BBC);
        
        when(store.resolveRecords(argThat(hasItems(one.getId()))))
            .thenReturn(ImmutableOptionalMap.<Id,EquivalenceRecord>of());
        
        when(resolver.resolveIds(argThat(hasItems(one.getId()))))
            .thenReturn(Futures.immediateFuture(Resolved.valueOf(ImmutableList.of(one))));
        
        ResolvedEquivalents<Content> resolved = equivResolver.resolveIds(ImmutableList.of(one.getId()),
                ImmutableSet.of(Publisher.PA), Annotation.defaultAnnotations()).get();

        assertTrue(resolved.isEmpty());
        
        verify(resolver).resolveIds(argThat(hasItems(one.getId())));
    }
    
}
