package org.atlasapi.equiv;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.atlasapi.application.ApplicationSources;
import org.atlasapi.application.SourceReadEntry;
import org.atlasapi.application.SourceStatus;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Publisher;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;


public class OutputContentMergerTest {

    private final OutputContentMerger merger = new OutputContentMerger();
    
    @Test
    public void testSortOfCommonSourceContentIsStable() {
        Brand one = new Brand("one","one",Publisher.BBC);
        one.setId(1);
        Brand two = new Brand("two", "two",Publisher.BBC);
        two.setId(2);
        Brand three = new Brand("three", "three",Publisher.TED);
        three.setId(3);
        
        one.copyWithEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(two), EquivalenceRef.valueOf(three)));
        two.setEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(one), EquivalenceRef.valueOf(three)));
        three.setEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(two), EquivalenceRef.valueOf(one)));
        
        ApplicationSources sources = ApplicationSources.EMPTY_SOURCES
                .copy().withPrecedence(true)
                .withReads(ImmutableList.of(
                        new SourceReadEntry(Publisher.BBC, SourceStatus.AVAILABLE_ENABLED),
                        new SourceReadEntry(Publisher.TED, SourceStatus.AVAILABLE_ENABLED)
                 ))
                .build();
        
        ImmutableList<Brand> contents = ImmutableList.of(one, two, three);
        
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(sources, contentList);
            assertThat(merged.size(), is(1));
            if (contentList.get(0).equals(three)) {
                assertThat(merged.get(0), is(contentList.get(1)));
            } else {
                assertThat(merged.get(0), is(contentList.get(0)));
            }
        }

    }

}
