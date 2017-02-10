package org.atlasapi.equiv;

import java.util.Arrays;
import java.util.List;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.ImageType;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.Publisher;

import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.applications.client.model.internal.ApplicationConfiguration;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OutputContentMergerTest {

    private final OutputContentMerger merger = new OutputContentMerger();
    private Application application = mock(Application.class);
    
    @Test
    public void testSortOfCommonSourceContentIsStable() {
        Brand one = brand(1L, "one",Publisher.BBC);
        Brand two = brand(2L, "two",Publisher.BBC);
        Brand three = brand(3L, "three",Publisher.TED);
        
        setEquivalent(one, two, three);
        setEquivalent(two, one, three);
        setEquivalent(three, two, one);

        when(application.getConfiguration()).thenReturn(configWithReads(Publisher.BBC, Publisher.TED));

        ImmutableList<Brand> contents = ImmutableList.of(one, two, three);
        
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(application, contentList);
            assertThat(merged.size(), is(1));
            if (contentList.get(0).equals(three)) {
                assertThat(contentList.toString(), merged.get(0), is(contentList.get(1)));
            } else {
                assertThat(contentList.toString(), merged.get(0), is(contentList.get(0)));
            }
        }

    }

    @Test
    public void testSourceSettingImagesBetweenGenericAndNonGeneric() {
        Brand one = brand(1L, "one", Publisher.BBC);
        Brand two = brand(2L, "two", Publisher.TED);

        Image test1 = new Image("test1");
        Image test2 = new Image("test2");

        test1.setAvailabilityStart(DateTime.now().minusDays(1));
        test1.setType(ImageType.GENERIC_IMAGE_CONTENT_PLAYER);
        test1.setAvailabilityEnd(DateTime.now());

        test2.setAvailabilityStart(DateTime.now().minusDays(1));
        test2.setAvailabilityEnd(DateTime.now());

        one.setImage(test1.getCanonicalUri());
        two.setImage(test2.getCanonicalUri());

        one.setImages(ImmutableList.of(test1));
        two.setImages(ImmutableList.of(test2));

        when(application.getConfiguration()).thenReturn(configWithReads(Publisher.BBC, Publisher.TED));

        List<Container> notChosen = Lists.newArrayList();
        notChosen.add(one);
        merger.mergeIn(application, two, notChosen);

        assertEquals(test2.getCanonicalUri(), Iterables.getOnlyElement(two.getImages()).getCanonicalUri());

    }

    @Test
    public void testMergedContentHasLowestIdOfSelectedContentInEquivalenceSet() {
        
        Brand one = brand(5L, "one", Publisher.BBC);
        Brand two = brand(2L, "two",Publisher.PA);
        Brand three = brand(10L, "three",Publisher.TED);
        
        setEquivalent(one, two, three);
        setEquivalent(two, one, three);
        setEquivalent(three, two, one);
        
        // Two is intentionally missing here. It will be in the `same_as` lists of content one and
        // two, but not in the filtered list of content that we are passing to the merger
        ImmutableList<Brand> contents = ImmutableList.of(one, three);

        when(application.getConfiguration())
                .thenReturn(configWithReads(Publisher.BBC, Publisher.TED));
        mergePermutations(contents, application, one, one.getId());

        when(application.getConfiguration())
                .thenReturn(configWithReads(Publisher.TED,Publisher.BBC));
        mergePermutations(contents, application, three, one.getId());
        
    }

    private Brand brand(long id, String uri, Publisher source) {
        Brand one = new Brand(uri,uri,source);
        one.setId(id);
        return one;
    }

    private void mergePermutations(ImmutableList<Brand> contents, Application application,
            Brand expectedContent, long expectedId) {
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(application, contentList);
            Brand mergedBrand = Iterables.getOnlyElement(merged);
            assertThat(mergedBrand, is(expectedContent));
            assertThat(mergedBrand.getId(), is(expectedId));
        }
    }

    private void setEquivalent(Content receiver, Content...equivalents) {
        receiver.setEquivalentTo(ImmutableSet.copyOf(Iterables.transform(
            ImmutableList.copyOf(equivalents), LookupRef.FROM_DESCRIBED)
        ));
    }

    private ApplicationConfiguration configWithReads(Publisher... publishers) {
        return ApplicationConfiguration.builder()
                .withPrecedence(Arrays.asList(publishers))
                .withEnabledWriteSources(ImmutableList.of())
                .build();
    }

}
