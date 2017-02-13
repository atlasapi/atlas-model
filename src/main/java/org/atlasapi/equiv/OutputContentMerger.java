package org.atlasapi.equiv;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.atlasapi.media.entity.AudienceStatistics;
import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Certificate;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.ContentGroup;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Film;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.ImageType;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Person;
import org.atlasapi.media.entity.Priority;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.ReleaseDate;
import org.atlasapi.media.entity.SimilarContentRef;
import org.atlasapi.media.entity.Subtitles;
import org.atlasapi.media.entity.TopicRef;
import org.atlasapi.media.entity.Version;

import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.common.stream.MoreCollectors;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;


public class OutputContentMerger {

    private static final Ordering<Episode> SERIES_ORDER = Ordering.from(new SeriesOrder());

    @SuppressWarnings("unchecked")
    public <T extends Described> List<T> merge(Application application, List<T> contents) {

        Ordering<Described> contentComparator = toContentOrdering(
                getCompleteOrdering(
                        application,
                        contents
                )
        );

        List<T> merged = Lists.newArrayListWithCapacity(contents.size());
        Set<T> processed = Sets.newHashSet();

        for (T content : contents) {
            if (processed.contains(content)) {
                continue;
            }
            List<T> same = contentComparator.sortedCopy(findSame(content, contents));
            processed.addAll(same);

            T chosen = same.get(0);

            chosen.setId(lowestId(chosen, same));

            // defend against broken transitive equivalence
            if (merged.contains(chosen)) {
                continue;
            }

            List<T> notChosen = same.subList(1, same.size());

            if (chosen instanceof Container) {
                mergeIn(application, (Container) chosen, (List<Container>) notChosen);
            }
            if (chosen instanceof Item) {
                mergeIn(application, (Item) chosen, (List<Item>) notChosen);
            }
            if (chosen instanceof ContentGroup) {
                mergeIn(application, (ContentGroup) chosen, (List<ContentGroup>) notChosen);
            }
            merged.add(chosen);
        }
        return merged;
    }

    private <T extends Described> Long lowestId(T chosen, List<T> same) {
        Ordering<Comparable> ordering = Ordering.natural().nullsLast();

        Long lowest = chosen.getId();
        for (T equivDescribed : same) {
            lowest = ordering.min(lowest, equivDescribed.getId());
        }

        return lowest;
    }

    @SuppressWarnings("unchecked")
    private <T extends Described> List<T> findSame(T brand, Iterable<T> contents) {
        List<T> same = Lists.newArrayList(brand);
        for (T possiblyEquivalent : contents) {
            if (!brand.equals(possiblyEquivalent) && possiblyEquivalent.isEquivalentTo(brand)) {
                same.add(possiblyEquivalent);
            }
        }
        return same;
    }

    private static Ordering<Described> toContentOrdering(final Ordering<Publisher> byPublisher) {
        return new Ordering<Described>() {
            @Override
            public int compare(Described o1, Described o2) {
                return byPublisher.compare(o1.getPublisher(), o2.getPublisher());
            }
        };
    }

    private Ordering<Publisher> getCompleteOrdering(
            Application application,
            Iterable<? extends Described> contents
    ) {

        List<Publisher> applicationPublishers = application.getConfiguration()
                .getReadPrecedenceOrdering()
                .sortedCopy(
                        application.getConfiguration()
                                .getEnabledReadSources()
                                .asList()
                );

        List<Publisher> otherPublishers = StreamSupport.stream(contents.spliterator(), false)
                .map(Described::getPublisher)
                .filter(publisher -> !applicationPublishers.contains(publisher))
                .collect(MoreCollectors.toImmutableList());

        return Ordering.explicit(ImmutableList.<Publisher>builder()
                .addAll(applicationPublishers)
                .addAll(otherPublishers)
                .build()
        );

    }

    private <T extends ContentGroup> void mergeIn(Application application, T chosen, Iterable<T> notChosen) {
        mergeDescribed(application, chosen, notChosen);
        for (ContentGroup contentGroup : notChosen) {
            for (ChildRef childRef : contentGroup.getContents()) {
                chosen.addContent(childRef);
            }
        }
        if (chosen instanceof Person) {
            Person person = (Person) chosen;
            ImmutableSet.Builder<String> quotes = ImmutableSet.builder();
            quotes.addAll(person.getQuotes());
            for (Person unchosen : Iterables.filter(notChosen, Person.class)) {
                quotes.addAll(unchosen.getQuotes());
                person.withName(person.name() != null ? person.name() : unchosen.name());
                person.setGivenName(person.getGivenName() != null ? person.getGivenName() : unchosen.getGivenName());
                person.setFamilyName(person.getFamilyName() != null ? person.getFamilyName() : unchosen.getFamilyName());
                person.setGender(person.getGender() != null ? person.getGender() : unchosen.getGender());
                person.setBirthDate(person.getBirthDate() != null ? person.getBirthDate() : unchosen.getBirthDate());
                person.setBirthPlace(person.getBirthPlace() != null ? person.getBirthPlace() : unchosen.getBirthPlace());
            }
            person.setQuotes(quotes.build());
        }
    }

    private <T extends Described> void mergeDescribed(Application application, T chosen, Iterable<T> notChosen) {
        mergeIdentified(application, chosen, notChosen);
        applyImagePrefs(application, chosen, notChosen);
        chosen.setRelatedLinks(
                projectFieldFromEquivalents(chosen, notChosen, Described::getRelatedLinks)
        );

        if (chosen.getTitle() == null) {
            chosen.setTitle(first(notChosen, TO_TITLE));
        }
        if (chosen.getDescription() == null) {
            chosen.setDescription(first(notChosen, TO_DESCRIPTION));
        }
        if (chosen.getLongDescription() == null) {
            chosen.setLongDescription(first(notChosen, TO_LONG_DESCRIPTION));
        }
        if (chosen.getMediumDescription() == null) {
            chosen.setMediumDescription(first(notChosen, TO_MEDIUM_DESCRIPTION));
        }
        if (chosen.getShortDescription() == null) {
            chosen.setShortDescription(first(notChosen, TO_SHORT_DESCRIPTION));
        }
        if (chosen.getAudienceStatistics() == null) {
            chosen.setAudienceStatistics(first(notChosen, TO_AUDIENCE_STATISTICS));
        }
        if (chosen.getPriority() == null) {
            chosen.setPriority(first(notChosen, TO_PRIORITY));
        }
        chosen.setRatings(projectFieldFromEquivalents(chosen, notChosen, Described::getRatings));

        chosen.setReviews(projectFieldFromEquivalents(chosen, notChosen, Described::getReviews));

        chosen.setGenres(projectFieldFromEquivalents(chosen, notChosen, Described::getGenres));

    }

    private <T extends Identified> void mergeIdentified(
            Application application,
            T chosen,
            Iterable<T> notChosen
    ) {
        chosen.setAliases(projectFieldFromEquivalents(chosen, notChosen, Identified::getAliases));
    }

    private <T extends Identified, P> Iterable<P> projectFieldFromEquivalents(T chosen,
            Iterable<T> notChosen, Function<T, Iterable<P>> projector) {
        return Iterables.concat(
                projector.apply(chosen),
                Iterables.concat(Iterables.transform(notChosen, projector))
        );
    }

    private <I extends Described, O> O first(
            Iterable<I> is,
            Function<? super I, ? extends O> transform,
            O defaultValue
    ) {
        return Iterables.getFirst(
                Iterables.filter(
                        Iterables.transform(is, transform),
                        Predicates.notNull()),
                defaultValue);
    }

    private <I extends Described, O> O first(Iterable<I> is, Function<? super I, ? extends O> transform) {
        return first(is, transform, null);
    }

    private <T extends Content> void mergeContent(Application application, T chosen, Iterable<T> notChosen) {
        mergeDescribed(application, chosen, notChosen);
        for (T notChosenItem : notChosen) {
            for (Clip clip : notChosenItem.getClips()) {
                chosen.addClip(clip);
            }
        }
        mergeTopics(chosen, notChosen);
        mergeKeyPhrases(chosen, notChosen);
        mergeSimilarContent(chosen, notChosen);
        mergeEvents(chosen, notChosen);

    }

    private <T extends Item> void mergeIn(Application application, T chosen, Iterable<T> notChosen) {
        mergeContent(application, chosen, notChosen);
        mergeVersions(application, chosen, notChosen);
        mergeReleaseDates(chosen, notChosen);
        if (chosen instanceof Film) {
            mergeFilmProperties(application, (Film) chosen, Iterables.filter(notChosen, Film.class));
        }
    }

    private <T extends Content> void mergeSimilarContent(T chosen, Iterable<T> notChosen) {
        if (chosen.getSimilarContent().isEmpty()) {
            chosen.setSimilarContent(first(notChosen,
                    TO_SIMILAR_CONTENT,
                    ImmutableSet.of()
            ));
        }
    }

    private <T extends Content> void mergeKeyPhrases(T chosen, Iterable<T> notChosen) {
        chosen.setKeyPhrases(projectFieldFromEquivalents(chosen, notChosen, Content::getKeyPhrases));
    }

    private <T extends Content> void mergeTopics(T chosen, Iterable<T> notChosen) {
        Function<T, Iterable<TopicRef>> topicRefsProjector = input ->
                Iterables.transform(
                        input.getTopicRefs(),
                        new TopicPublisherSetter(input)
                );

        chosen.setTopicRefs(projectFieldFromEquivalents(chosen, notChosen, topicRefsProjector));
    }

    private <T extends Content> void mergeEvents(T chosen, Iterable<T> notChosen) {
        chosen.setEventRefs(projectFieldFromEquivalents(chosen, notChosen, Content::events));
    }

    private <T extends Item> void mergeReleaseDates(T chosen, Iterable<T> notChosen) {
        Builder<ReleaseDate> releases = ImmutableSet.<ReleaseDate>builder().addAll(chosen.getReleaseDates());
        for (T item : notChosen) {
            releases.addAll(item.getReleaseDates());
        }
        chosen.setReleaseDates(releases.build());
    }



    private void mergeFilmProperties(Application application, Film chosen, Iterable<Film> notChosen) {
        Builder<Subtitles> subtitles = ImmutableSet.<Subtitles>builder().addAll(chosen.getSubtitles());
        Builder<String> languages = ImmutableSet.<String>builder().addAll(chosen.getLanguages());


        if (chosen.getCertificates().isEmpty()) {
            chosen.setCertificates(first(notChosen, TO_CERTIFICATES, ImmutableSet.of()));
        }

        for (Film film : notChosen) {
            subtitles.addAll(film.getSubtitles());
            languages.addAll(film.getLanguages());
        }

        chosen.setSubtitles(subtitles.build());
        chosen.setLanguages(languages.build());


        if (application.getConfiguration().isPeoplePrecedenceEnabled()) {
            Iterable<Film> all = Iterables.concat(ImmutableList.of(chosen), notChosen);
            List<Film> topFilmMatches =
                    toContentOrdering(application.getConfiguration().getPeopleReadPrecedenceOrdering())
                            .leastOf(Iterables.filter(all, HAS_PEOPLE), 1);

            if (!topFilmMatches.isEmpty()) {
                Film top = topFilmMatches.get(0);
                chosen.setPeople(top.getPeople());
            }
        }
    }

    private <T extends Described> void applyImagePrefs(Application application, T chosen, Iterable<T> notChosen) {
        if (application.getConfiguration().isImagePrecedenceEnabled()) {
            Iterable<T> all = Iterables.concat(ImmutableList.of(chosen), notChosen);
            List<T> topImageMatches =
                    toContentOrdering(application.getConfiguration().getImageReadPrecedenceOrdering())
                            .leastOf(Iterables.filter(
                                    all,
                                    HAS_AVAILABLE_AND_NOT_GENERIC_IMAGE_CONTENT_PLAYER_SET),
                                    1);

            if (!topImageMatches.isEmpty()) {
                T top = topImageMatches.get(0);
                chosen.setImage(top.getImage());
                chosen.setThumbnail(top.getThumbnail());
                chosen.setImages(top.getImages());
            } else {
                chosen.setImage(null);
            }
        }
    }

    private <T extends Item> void mergeVersions(Application application, T chosen, Iterable<T> notChosen) {
        // if chosen has broadcasts, merge the set of broadcasts from notChosen
        Set<Broadcast> chosenBroadcasts = Sets.newHashSet(Iterables.concat(Iterables.transform(chosen.getVersions(), Version.TO_BROADCASTS)));
        if (!chosenBroadcasts.isEmpty()) {
            List<T> notChosenOrdered =
                    toContentOrdering(getCompleteOrdering(application, notChosen))
                            .sortedCopy(notChosen);
            for (Broadcast chosenBroadcast : chosenBroadcasts) {
                matchAndMerge(chosenBroadcast, notChosenOrdered);
            }
        }
        for (T notChosenItem : notChosen) {
            for (Version version : notChosenItem.getVersions()) {
                // TODO When we have more granular precedence this broadcast masking can be removed
                version.setBroadcasts(Sets.<Broadcast>newHashSet());
                chosen.addVersion(version);
            }
        }
    }

    private <T extends Item> void matchAndMerge(final Broadcast chosenBroadcast, List<T> notChosen) {
        List<Broadcast> equivBroadcasts = Lists.newArrayList();
        for (T notChosenItem : notChosen) {
            Iterable<Broadcast> notChosenBroadcasts = Iterables.concat(
                    Iterables.transform(notChosenItem.getVersions(), Version.TO_BROADCASTS)
            );
            Optional<Broadcast> matched = Iterables.tryFind(notChosenBroadcasts,
                    input -> chosenBroadcast.getBroadcastOn().equals(input.getBroadcastOn())
                            && chosenBroadcast.getTransmissionTime().equals(input.getTransmissionTime()));
            if (matched.isPresent()) {
                equivBroadcasts.add(matched.get());
            }
        }
        // equivB'casts = list of matched broadcasts, ordered by precedence
        for (Broadcast equiv : equivBroadcasts) {
            mergeBroadcast(chosenBroadcast, equiv);
        }
    }

    private void mergeBroadcast(Broadcast chosen, Broadcast toMerge) {
        chosen.addAliases(toMerge.getAliases());
        chosen.addAliasUrls(toMerge.getAliasUrls());

        if (chosen.getRepeat() == null && toMerge.getRepeat() != null) {
            chosen.setRepeat(toMerge.getRepeat());
        }
        if (chosen.getScheduleDate() == null && toMerge.getScheduleDate() != null) {
            chosen.setScheduleDate(toMerge.getScheduleDate());
        }
        if (chosen.getSourceId() == null && toMerge.getSourceId() != null) {
            chosen.withId(toMerge.getSourceId());
        }
        if (chosen.getSubtitled() == null && toMerge.getSubtitled() != null) {
            chosen.setSubtitled(toMerge.getSubtitled());
        }
        if (chosen.getSigned() == null && toMerge.getSigned() != null) {
            chosen.setSigned(toMerge.getSigned());
        }
        if (chosen.getAudioDescribed() == null && toMerge.getAudioDescribed() != null) {
            chosen.setAudioDescribed(toMerge.getAudioDescribed());
        }
        if (chosen.getHighDefinition() == null && toMerge.getHighDefinition() != null) {
            chosen.setHighDefinition(toMerge.getHighDefinition());
        }
        if (chosen.getWidescreen() == null && toMerge.getWidescreen() != null) {
            chosen.setWidescreen(toMerge.getWidescreen());
        }
        if (chosen.getSurround() == null && toMerge.getSurround() != null) {
            chosen.setSurround(toMerge.getSurround());
        }
        if (chosen.getLive() == null && toMerge.getLive() != null) {
            chosen.setLive(toMerge.getLive());
        }
        if (chosen.getNewSeries() == null && toMerge.getNewSeries() != null) {
            chosen.setNewSeries(toMerge.getNewSeries());
        }
        if (chosen.getPremiere() == null && toMerge.getPremiere() != null) {
            chosen.setPremiere(toMerge.getPremiere());
        }
        if (chosen.getBlackoutRestriction() == null && toMerge.getBlackoutRestriction() != null) {
            chosen.setBlackoutRestriction(toMerge.getBlackoutRestriction());
        }
        if (chosen.getRevisedRepeat() == null && toMerge.getRevisedRepeat() != null){
            chosen.setRevisedRepeat(toMerge.getRevisedRepeat());
        }
    }

    private static final Predicate<Described> HAS_AVAILABLE_AND_NOT_GENERIC_IMAGE_CONTENT_PLAYER_SET = content -> {
        if (content.getImage() == null) {
            return false;
        }
        return isImageAvailableAndNotGeneric(content.getImage(), content.getImages());
    };

    private static boolean isImageAvailableAndNotGeneric(String imageUri, Iterable<Image> images) {

        // Fneh. Image URIs differ between the image attribute and the canonical URI on Images.
        // See PaProgrammeProcessor for why.
        String rewrittenUri = imageUri.replace("http://images.atlasapi.org/pa/",
                "http://images.atlas.metabroadcast.com/pressassociation.com/");

        // If there is a corresponding Image object for this URI, we check its availability and ensure
        // that it is not generic.
        for (Image image : images) {
            if (image.getCanonicalUri().equals(rewrittenUri)) {
                return Image.IS_AVAILABLE.apply(image)
                        && !ImageType.GENERIC_IMAGE_CONTENT_PLAYER.equals(image.getType());
            }
        }
        // Otherwise, we can only assume the image is available as we know no better
        return true;
    }

    private static final Predicate<Film> HAS_PEOPLE = film -> film.getPeople() != null && !film.getPeople().isEmpty();
    private static final Function<Described,String> TO_TITLE = input -> input == null ? null : input.getTitle();
    private static final Function<Described, String> TO_DESCRIPTION = input -> input == null ? null : input.getDescription();
    private static final Function<Described, String> TO_LONG_DESCRIPTION = input -> input == null ? null : input.getLongDescription();
    private static final Function<Described, String> TO_MEDIUM_DESCRIPTION = input -> input == null ? null : input.getMediumDescription();
    private static final Function<Described, String> TO_SHORT_DESCRIPTION = input -> input == null ? null : input.getShortDescription();
    private static final Function<Described, AudienceStatistics> TO_AUDIENCE_STATISTICS = input -> input == null ? null : input.getAudienceStatistics();
    private static final Function<Film, Set<Certificate>> TO_CERTIFICATES = input -> input == null || input.getCertificates().isEmpty() ? null : input.getCertificates();
    private static final Function<Content, List<SimilarContentRef>> TO_SIMILAR_CONTENT = content -> content.getSimilarContent().isEmpty() ? null : content.getSimilarContent();
    private static final Function<Described, Priority> TO_PRIORITY = input -> input == null ? null : input.getPriority();

    public <T extends Item> void mergeIn(Application application, Container chosen, List<Container> notChosen) {
        mergeContent(application, chosen, notChosen);
    }

    enum ItemIdStrategy {

        SERIES_EPISODE_NUMBER {

            @Override
            public Predicate<Item> match() {
                return item -> {
                    if (item instanceof Episode) {
                        Episode episode = (Episode) item;
                        return episode.getSeriesNumber() != null && episode.getEpisodeNumber() != null;
                    }
                    return false;
                };
            }

            @Override
            @SuppressWarnings("unchecked")
            public <T extends Item> Iterable<T> merge(List<T> items, List<T> matches) {
                Map<SeriesAndEpisodeNumber, Episode> chosenItemLookup = Maps.newHashMap();
                for (T item : Iterables.concat(items, matches)) {
                    Episode episode = (Episode) item;
                    SeriesAndEpisodeNumber se = new SeriesAndEpisodeNumber(episode);
                    if (!chosenItemLookup.containsKey(se)) {
                        chosenItemLookup.put(se, episode);
                    } else {
                        Item chosen = chosenItemLookup.get(se);
                        for (Clip clip : item.getClips()) {
                            chosen.addClip(clip);
                        }
                    }
                }

                return (Iterable<T>) SERIES_ORDER.immutableSortedCopy(chosenItemLookup.values());
            }
        };

        protected abstract Predicate<Item> match();

        static ItemIdStrategy findBest(Iterable<? extends Item> items) {
            if (Iterables.all(items, ItemIdStrategy.SERIES_EPISODE_NUMBER.match())) {
                return SERIES_EPISODE_NUMBER;
            }
            return null;
        }

        public abstract <T extends Item> Iterable<T> merge(List<T> items, List<T> matches);
    }

    private final static class TopicPublisherSetter implements Function<TopicRef, TopicRef> {

        private final Content publishedContent;

        public TopicPublisherSetter(Content publishedContent) {
            this.publishedContent = publishedContent;
        }

        @Override
        public TopicRef apply(TopicRef input) {
            input.setPublisher(publishedContent.getPublisher());
            return input;
        }
    }
}
