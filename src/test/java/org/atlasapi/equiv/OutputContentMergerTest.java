package org.atlasapi.equiv;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.LookupRef;
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
        Brand two = new Brand("two", "two",Publisher.BBC);
        Brand three = new Brand("three", "three",Publisher.TED);
        
        one.copyWithEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(two), EquivalenceRef.valueOf(three)));
        two.setEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(one), EquivalenceRef.valueOf(three)));
        three.setEquivalentTo(ImmutableSet.of(EquivalenceRef.valueOf(two), EquivalenceRef.valueOf(one)));
        
        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
            .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));
        
        ImmutableList<Brand> contents = ImmutableList.of(one, two, three);
        
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(config, contentList);
            assertThat(merged.size(), is(1));
            if (contentList.get(0).equals(three)) {
                assertThat(merged.get(0), is(contentList.get(1)));
            } else {
                assertThat(merged.get(0), is(contentList.get(0)));
            }
        }

    }

}
