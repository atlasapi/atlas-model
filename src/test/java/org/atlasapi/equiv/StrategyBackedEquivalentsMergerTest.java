package org.atlasapi.equiv;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyCollectionOf;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.atlasapi.application.ApplicationSources;
import org.atlasapi.application.SourceReadEntry;
import org.atlasapi.application.SourceStatus;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@RunWith(MockitoJUnitRunner.class)
public class StrategyBackedEquivalentsMergerTest {

    @SuppressWarnings("unchecked")
    private final EquivalentsMergeStrategy<Content> strategy
        = mock(EquivalentsMergeStrategy.class);
    private final StrategyBackedEquivalentsMerger<Content> merger
        = new StrategyBackedEquivalentsMerger<Content>(strategy);
    
    private final ApplicationSources nonMergingSources = ApplicationSources.EMPTY_SOURCES
            .copy().withPrecedence(false).build();
    private final ApplicationSources mergingSources = ApplicationSources.EMPTY_SOURCES
            .copy().withPrecedence(true)
            .withReads(ImmutableList.of(
                    new SourceReadEntry(Publisher.BBC, SourceStatus.AVAILABLE_ENABLED),
                    new SourceReadEntry(Publisher.TED, SourceStatus.AVAILABLE_ENABLED)
             ))
            .build();
    
    @Test
    public void testDoesntMergeForNonMergingConfig() {
        List<Content> merged = merger.merge(ImmutableSet.<Content>of(), 
                nonMergingSources);
        
        assertTrue(merged.isEmpty());
        veryifyNoMerge(nonMergingSources);
    }
    
    @Test
    public void testDoesntMergeForEmptyEquivalenceSet() {
        List<Content> merged = merger.merge(ImmutableSet.<Content>of(), 
                mergingSources);
        
        assertTrue(merged.isEmpty());
        veryifyNoMerge(mergingSources);
    }

    @Test
    public void testDoesntMergeForSingletonEquivalenceSet() {
        Content brand = new Brand();
        List<Content> merged = merger.merge(ImmutableSet.of(brand), 
                mergingSources);
        
        assertThat(merged.size(), is(1));
        veryifyNoMerge(mergingSources);
    }

    private void veryifyNoMerge(ApplicationSources sources) {
        verify(strategy, never()).merge(
            argThat(any(Content.class)), 
            anyCollectionOf(Content.class), 
            argThat(is(sources))
        );
    }
    
    @Test
    @SuppressWarnings("unchecked")
    public void testMergeSortingIsStable() {
        
        Brand one = new Brand("one","one",Publisher.BBC);
        Brand two = new Brand("two", "two",Publisher.BBC);
        Brand three = new Brand("three", "three",Publisher.TED);
        
        ImmutableList<Brand> contents = ImmutableList.of(one, two, three);
        
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            
            when(strategy.merge(
                argThat(any(Content.class)), 
                anyCollectionOf(Content.class), 
                argThat(is(mergingSources))
            )).thenReturn(one);
            
            merger.merge(contentList, mergingSources);
            
            if (contentList.get(0).equals(one)) {
                verify(strategy)
                    .merge(one, ImmutableList.of(two, three), mergingSources);
            } else if (contentList.get(0).equals(two)) {
                verify(strategy)
                    .merge(two, ImmutableList.of(one, three), mergingSources);
            } else {
                verify(strategy)
                    .merge(contentList.get(1), ImmutableList.of(contentList.get(2), three), mergingSources);
            }
            
            reset(strategy);
        }
    }

}
