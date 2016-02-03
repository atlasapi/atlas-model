package org.atlasapi.equiv;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.atlasapi.application.v3.ApplicationConfiguration;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.ImageType;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class OutputContentMergerTest {

    private final OutputContentMerger merger = new OutputContentMerger();
    
    @Test
    public void testSortOfCommonSourceContentIsStable() {
        Brand one = brand(1L, "one",Publisher.BBC);
        Brand two = brand(2L, "two",Publisher.BBC);
        Brand three = brand(3L, "three",Publisher.TED);
        
        setEquivalent(one, two, three);
        setEquivalent(two, one, three);
        setEquivalent(three, two, one);
        
        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
            .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));
        
        ImmutableList<Brand> contents = ImmutableList.of(one, two, three);
        
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(config, contentList);
            assertThat(merged.size(), is(1));
            if (contentList.get(0).equals(three)) {
                assertThat(contentList.toString(), merged.get(0), is(contentList.get(1)));
            } else {
                assertThat(contentList.toString(), merged.get(0), is(contentList.get(0)));
            }
        }

    }

    @Test
    public void testSourceSettingImages() {
        Brand one = brand(1L, "one", Publisher.BBC);
        Brand two = brand(2L, "two", Publisher.TED);

        Image test1 = new Image("test1");
        Image test2 = new Image("test2");

        test1.setAvailabilityStart(DateTime.now().minusDays(1));
        test1.setType(ImageType.GENERIC);
        test1.setAvailabilityEnd(DateTime.now());

        test2.setAvailabilityStart(DateTime.now().minusDays(1));
        test2.setAvailabilityEnd(DateTime.now());

        one.setImage(test1.getCanonicalUri());
        two.setImage(test2.getCanonicalUri());

        one.setImages(ImmutableList.of(test1));
        two.setImages(ImmutableList.of(test2));


        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED)).copyWithImagePrecedenceEnabled(true);


        List<Container> notChosen = Lists.newArrayList();
        notChosen.add(one);
        merger.mergeIn(config, two, notChosen);

        assertEquals(two.getCanonicalUri(), Iterables.getOnlyElement(two.getImages()).getCanonicalUri());

    }

    @Test
    public void testMergedContentHasLowestIdOfContentInEquivalenceSet() {
        
        Brand one = brand(5L, "one", Publisher.BBC);
        Brand two = brand(2L, "two",Publisher.PA);
        Brand three = brand(10L, "three",Publisher.TED);
        
        setEquivalent(one, two, three);
        setEquivalent(two, one, three);
        setEquivalent(three, two, one);
        
        //two is intentionally missing here
        ImmutableList<Brand> contents = ImmutableList.of(one, three);

        ApplicationConfiguration config = configWithPrecedence(Publisher.BBC, Publisher.TED);
        mergePermutations(contents, config, one, two.getId());

        config = configWithPrecedence(Publisher.TED,Publisher.BBC);
        mergePermutations(contents, config, three, two.getId());
        
    }

    private Brand brand(long id, String uri, Publisher source) {
        Brand one = new Brand(uri,uri,source);
        one.setId(id);
        return one;
    }

    private void mergePermutations(ImmutableList<Brand> contents, ApplicationConfiguration config,
            Brand expectedContent, long expectedId) {
        for (List<Brand> contentList : Collections2.permutations(contents)) {
            List<Brand> merged = merger.merge(config, contentList);
            Brand mergedBrand = Iterables.getOnlyElement(merged);
            assertThat(mergedBrand, is(expectedContent));
            assertThat(mergedBrand.getId(), is(expectedId));
        }
    }
    
    private ApplicationConfiguration configWithPrecedence(Publisher...publishers) {
        return ApplicationConfiguration.defaultConfiguration()
            .copyWithPrecedence(ImmutableList.copyOf(publishers));
    }
    
    private void setEquivalent(Content receiver, Content...equivalents) {
        receiver.setEquivalentTo(ImmutableSet.copyOf(Iterables.transform(
            ImmutableList.copyOf(equivalents), LookupRef.FROM_DESCRIBED)
        ));
    }

}
