package org.atlasapi.equiv;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Locale;

import javax.swing.plaf.PanelUI;

import org.atlasapi.application.v3.ApplicationConfiguration;
import org.atlasapi.media.entity.Author;
import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.ContentGroup;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Distribution;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.ImageType;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Language;
import org.atlasapi.media.entity.LocalizedTitle;
import org.atlasapi.media.entity.LookupRef;
import org.atlasapi.media.entity.Person;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Review;

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


        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED)).copyWithImagePrecedenceEnabled(true);


        List<Container> notChosen = Lists.newArrayList();
        notChosen.add(one);
        merger.mergeIn(config, two, notChosen);

        assertEquals(test2.getCanonicalUri(), Iterables.getOnlyElement(two.getImages()).getCanonicalUri());

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

    @Test
    public <T extends Described> void distributionsMergeCorrectly() {
        T item1 = makeItem("uri", Publisher.AMAZON_UNBOX);
        T item2 = makeItem("uri2", Publisher.PA);

        Distribution distribution = Distribution.builder()
                .withDistributor("distributor")
                .withReleaseDate(DateTime.now())
                .withFormat("format")
                .build();

        item2.setDistributions(ImmutableList.of(distribution));

        setEquivalent(item1, item2);

        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));

        List<T> items = Lists.newArrayList();
        items.add(item1);
        items.add(item2);
        List<T> merged = merger.merge(config, items);
        T merged1 = merged.get(0);

        for (Distribution mergedDistribution : merged1.getDistributions()) {
            assertEquals(distribution.getDistributor(), mergedDistribution.getDistributor());
            assertEquals(distribution.getFormat(), mergedDistribution.getFormat());
            assertEquals(distribution.getReleaseDate(), mergedDistribution.getReleaseDate());
        }

    }

    @Test
    public <T extends Described> void reviewsMergeCorrectly() {
        T item1 = makeItem("uri", Publisher.AMAZON_UNBOX);
        T item2 = makeItem("uri2", Publisher.PA);

        Review review = new Review(new Locale("usa"), "review");
        review.setType("type");
        review.setAuthor(Author.builder()
                .withAuthorInitials("initials")
                .withAuthorName("name")
                .build()
        );

        item2.setReviews(ImmutableList.of(review));

        setEquivalent(item1, item2);

        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));

        List<T> items = Lists.newArrayList();
        items.add(item1);
        items.add(item2);
        List<T> merged = merger.merge(config, items);
        T merged1 = merged.get(0);

        for (Review mergedReview1 : merged1.getReviews()) {
            assertEquals(review.getType(), mergedReview1.getType());
            assertEquals(review.getReview(), mergedReview1.getReview());
            assertEquals(review.getLocale(), mergedReview1.getLocale());

            assertEquals(
                    review.getAuthor().getAuthorInitials(),
                    mergedReview1.getAuthor().getAuthorInitials()
            );

            assertEquals(
                    review.getAuthor().getAuthorName(),
                    mergedReview1.getAuthor().getAuthorName()
            );

        }

    }

    @Test
    public <T extends Described> void peopleMergeCorrectly() {
        Person person1 = new Person();
        Person person2 = new Person();

        person1.setPublisher(Publisher.PA);
        person2.setPublisher(Publisher.AMAZON_UNBOX);

        person1.setGivenName("bill");
        person1.setFamilyName("Moore");
        person1.setGender("male");
        person1.setBirthDate(DateTime.now());
        person1.setBirthPlace("Canada");

        person2.setQuotes(ImmutableList.of("one", "two", "three"));
        person2.setPseudoForename("billy");
        person2.setPseudoSurname("Moorey");
        person2.setAdditionalInfo("additional info");
        person2.setBilling("billing");
        person2.setSource("source");
        person2.setSourceTitle("sourcetitle");

        setEquivalent(person1, person2);

        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));

        List<Person> people = Lists.newArrayList();
        people.add(person1);
        people.add(person2);
        List<Person> merged = (List<Person>)merger.merge(config, (List<T>)people);
        Person merged1 = merged.get(0);

        assertEquals(person1.getGivenName(), merged1.getGivenName());
        assertEquals(person1.getFamilyName(), merged1.getFamilyName());
        assertEquals(person1.getGender(), merged1.getGender());
        assertEquals(person1.getBirthDate(), merged1.getBirthDate());
        assertEquals(person1.getBirthPlace(), merged1.getBirthPlace());
        assertEquals(person2.getQuotes(), merged1.getQuotes());
        assertEquals(person2.getPseudoForename(), merged1.getPseudoForename());
        assertEquals(person2.getPseudoSurname(), merged1.getPseudoSurname());
        assertEquals(person2.getAdditionalInfo(), merged1.getAdditionalInfo());
        assertEquals(person2.getBilling(), merged1.getBilling());
        assertEquals(person2.getSource(), merged1.getSource());
        assertEquals(person2.getSourceTitle(), merged1.getSourceTitle());
    }

    @Test
    public <T extends Described> void languageAndDubbingMergeCorrectly() {
        T item1 = makeItem("uri", Publisher.AMAZON_UNBOX);
        T item2 = makeItem("uri2", Publisher.PA);

        Language language = Language.builder()
                .withCode("some code")
                .withDisplay("Some display")
                .withDubbing("some dubbing")
                .build();

        item2.setLanguage(language);

        setEquivalent(item1, item2);

        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));

        List<T> items = Lists.newArrayList();
        items.add(item1);
        items.add(item2);
        List<T> merged = merger.merge(config, items);
        T merged1 = merged.get(0);
        Language mergedLanguage = merged1.getLanguage();

        assertEquals(language.getCode(), mergedLanguage.getCode());
        assertEquals(language.getDisplay(), mergedLanguage.getDisplay());
        assertEquals(language.getDubbing(), mergedLanguage.getDubbing());
    }

    @Test
    public <T extends Described> void localizedTitlesMergeCorrectly() {
        T item1 = makeItem("uri", Publisher.AMAZON_UNBOX);
        T item2 = makeItem("uri2", Publisher.PA);

        LocalizedTitle localizedTitle = new LocalizedTitle();
        localizedTitle.setTitle("some title");
        localizedTitle.setType("some type");
        localizedTitle.setLocale(new Locale("usa"));

        List<LocalizedTitle> localizedTitles = ImmutableList.of(localizedTitle);
        item2.setLocalizedTitles(localizedTitles);

        setEquivalent(item1, item2);

        ApplicationConfiguration config = ApplicationConfiguration.defaultConfiguration()
                .copyWithPrecedence(ImmutableList.of(Publisher.BBC, Publisher.TED));

        List<T> items = Lists.newArrayList();
        items.add(item1);
        items.add(item2);
        List<T> merged = merger.merge(config, items);
        T merged1 = merged.get(0);

        LocalizedTitle mergedLocalizedTitle =
                (LocalizedTitle) merged1.getLocalizedTitles().toArray()[0];

        assertEquals(localizedTitle.getTitle(), mergedLocalizedTitle.getTitle());
        assertEquals(localizedTitle.getType(), mergedLocalizedTitle.getType());
        assertEquals(localizedTitle.getLocale(), mergedLocalizedTitle.getLocale());
    }

    private <T extends Described> T makeItem(String uri, Publisher publisher) {
        T item = (T) new Item();
        item.setCanonicalUri(uri);
        item.setImages(ImmutableSet.of());
        item.setPublisher(publisher);

        return item;
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

    private <T extends Described> void setEquivalent(T receiver, T...equivalents) {
        receiver.setEquivalentTo(ImmutableSet.copyOf(Iterables.transform(
                ImmutableList.copyOf(equivalents), LookupRef.FROM_DESCRIBED)
        ));
    }

}
