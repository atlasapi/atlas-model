/* Copyright 2010 Meta Broadcast Ltd

 Licensed under the Apache License, Version 2.0 (the "License"); you
 may not use this file except in compliance with the License. You may
 obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 implied. See the License for the specific language governing
 permissions and limitations under the License. */
package org.atlasapi.media.entity;

import java.util.List;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.TransportType;

public abstract class Content extends Described {

    private Set<Version> versions = Sets.newHashSet();
    private transient String readHash;
    private ImmutableList<Clip> clips = ImmutableList.of();
    private Set<KeyPhrase> keyPhrases = ImmutableSet.of();
    private ImmutableList<TopicRef> topicRefs = ImmutableList.of();
    private ImmutableList<ContentGroupRef> contentGroupRefs = ImmutableList.of();
    private ImmutableList<SimilarContentRef> similarContent = ImmutableList.of();
    private List<CrewMember> people = Lists.newArrayList();
    private Set<String> languages = ImmutableSet.of();
    private Set<Certificate> certificates = ImmutableSet.of();
    private Integer year = null;
    private Boolean genericDescription;
    private ImmutableList<EventRef> events = ImmutableList.of();
    private Integer editorialPriority;
    private Set<Country> countriesOfOrigin = ImmutableSet.of();

    public Content(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
    }

    public Content() { /*
         * some legacy code still requires a default constructor
         */

    }

    public ChildRef childRef() {
        return new ChildRef(this.getId(), this.getCanonicalUri(), this.getSortKey(), this.getThisOrChildLastUpdated(), EntityType.from(this.getClass()));
    }

    public List<Clip> getClips() {
        return clips;
    }

    public void setSimilarContent(Iterable<SimilarContentRef> similarContent) {
        this.similarContent = ImmutableList.copyOf(similarContent);
    }
    
    public void addSimilarContent(SimilarContentRef similarContent) {
        this.similarContent = ImmutableList.<SimilarContentRef>builder()
                                           .addAll(this.similarContent)
                                           .add(similarContent)
                                           .build();
    }
    
    public List<SimilarContentRef> getSimilarContent() {
        return similarContent;
    }
    
    public void setTopicRefs(Iterable<TopicRef> topicRefs) {
        this.topicRefs = ImmutableList.copyOf(topicRefs);
    }

    public void addTopicRef(TopicRef topicRef) {
        topicRefs = ImmutableList.<TopicRef>builder().add(topicRef).addAll(topicRefs).build();
    }

    public void addTopicRefs(Iterable<TopicRef> topicRefs) {
        this.topicRefs = ImmutableList.<TopicRef>builder().addAll(this.topicRefs).addAll(topicRefs).build();
    }

    public List<TopicRef> getTopicRefs() {
        return topicRefs;
    }

    public void setContentGroupRefs(Iterable<ContentGroupRef> contentGroupRefs) {
        this.contentGroupRefs = ImmutableList.copyOf(contentGroupRefs);
    }

    public void addContentGroup(ContentGroupRef contentGroupRef) {
        contentGroupRefs = ImmutableList.<ContentGroupRef>builder().add(contentGroupRef).addAll(contentGroupRefs).build();
    }

    public List<ContentGroupRef> getContentGroupRefs() {
        return contentGroupRefs;
    }

    public void setClips(Iterable<Clip> clips) {
        this.clips = ImmutableList.copyOf(clips);
        for (Clip clip : clips) {
            clip.setClipOf(this.getCanonicalUri());
        }
    }

    public void addClip(Clip clip) {
        List<Clip> all = Lists.newArrayList(clips);
        all.add(clip);
        setClips(all);
    }
    
    public void addClips(List<Clip> clips) {
        List<Clip> all = Lists.newArrayList(this.clips);
        all.addAll(clips);
        setClips(all);
    }

    public Set<KeyPhrase> getKeyPhrases() {
        return keyPhrases;
    }

    public void setKeyPhrases(Iterable<KeyPhrase> phrases) {
        keyPhrases = ImmutableSet.copyOf(phrases);
    }

    public void addKeyPhrase(KeyPhrase phrase) {
        keyPhrases = ImmutableSet.<KeyPhrase>builder().add(phrase).addAll(keyPhrases).build();
    }

    public List<CrewMember> people() {
        return people;
    }

    public List<Actor> actors() {
        return Lists.<Actor>newArrayList(Iterables.filter(people, Actor.class));
    }

    public void addPerson(CrewMember person) {
        people.add(person);
    }

    public void setPeople(List<CrewMember> people) {
        this.people = people;
    }
    
    public void setGenericDescription(Boolean genericDescription) {
        this.genericDescription = genericDescription;
    }
    
    public Boolean getGenericDescription() {
        return genericDescription;
    }
    
    public void setEvents(Iterable<Event> events) {
        this.events = ImmutableList.copyOf(Iterables.transform(events, EventRef.toEventRef()));
    }
    
    public void setEventRefs(Iterable<EventRef> events) {
        this.events = ImmutableList.copyOf(events);
    }
    
    public ImmutableList<EventRef> events() {
        return events;
    }

    public Set<Country> getCountriesOfOrigin() {
        return countriesOfOrigin;
    }

    public void setCountriesOfOrigin(Set<Country> countries) {
        this.countriesOfOrigin = ImmutableSet.copyOf(countries);
    }


    public static void copyTo(Content from, Content to) {
        Described.copyTo(from, to);
        to.clips = ImmutableList.copyOf(Iterables.transform(from.clips, Clip.COPIES));
        to.keyPhrases = from.keyPhrases;
        to.relatedLinks = from.relatedLinks;
        to.topicRefs = from.topicRefs;
        to.readHash = from.readHash;
        to.people = Lists.newArrayList(Iterables.transform(from.people, CrewMember.COPY));
        to.languages = from.languages;
        to.certificates = from.certificates;
        to.year = from.year;
        to.genericDescription = from.genericDescription;
        to.similarContent = from.similarContent;
        to.events = ImmutableList.copyOf(Iterables.transform(from.events, EventRef.COPY));
        to.countriesOfOrigin = ImmutableSet.copyOf(from.countriesOfOrigin);
        copyToWithVersions(
                from,
                to,
                Sets.newHashSet(Iterables.transform(from.versions, Version.COPY))
        );
    }

    public void setReadHash(String readHash) {
        this.readHash = readHash;
    }

    public boolean hashChanged(String newHash) {
        return readHash == null || !this.readHash.equals(newHash);
    }

    public void addVersion(Version version) {
        if (version.getProvider() == null) {
            version.setProvider(publisher);
        }
        versions.add(version);
    }

    @RdfProperty(relation = true, uri = "version")
    public Set<Version> getVersions() {
        return versions;
    }

    public Set<Version> nativeVersions() {
        return Sets.filter(versions, new Predicate<Version>() {

            @Override
            public boolean apply(Version v) {
                return publisher.equals(v.getProvider());
            }
        });
    }

    public void setVersions(Set<Version> versions) {
        this.versions = Sets.newHashSet();
        addVersions(versions);
    }

    public void addVersions(Set<Version> versions) {
        for (Version version : versions) {
            addVersion(version);
        }
    }

    public boolean removeVersion(Version version) {
        return versions.remove(version);
    }

    private List<Location> locations() {
        List<Location> locations = Lists.newArrayList();
        for (Version version : getVersions()) {
            for (Encoding encoding : version.getManifestedAs()) {
                for (Location location : encoding.getAvailableAt()) {
                    locations.add(location);
                }
            }
        }

        return locations;
    }

    public boolean isAvailable() {
        for (Location location : locations()) {
            if (location.getAvailable()) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmbeddable() {
        for (Location location : locations()) {
            if (location.getTransportType() != null && TransportType.EMBED.equals(location.getTransportType())) {
                return true;
            }
        }
        return false;
    }

    public Iterable<Broadcast> flattenBroadcasts() {
        return Iterables.concat(Iterables.transform(versions, Version.TO_BROADCASTS));
    }

    public Iterable<Location> flattenLocations() {
        return Iterables.concat(Iterables.transform(Iterables.concat(Iterables.transform(versions, Version.TO_ENCODINGS)), Encoding.TO_LOCATIONS));
    }

    private static void copyToWithVersions(Content from, Content to, Set<Version> versions) {
        to.versions = versions;
    }

    public static final Function<Item, Iterable<Broadcast>> FLATTEN_BROADCASTS = new Function<Item, Iterable<Broadcast>>() {

        @Override
        public Iterable<Broadcast> apply(Item input) {
            return input.flattenBroadcasts();
        }
    };

    protected String getSortKey() {
        return SortKey.DEFAULT.name();
    }
    
    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Iterable<String> languages) {
        this.languages = ImmutableSet.copyOf(languages);
    }

    public Set<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(Iterable<Certificate> certificates) {
        this.certificates = ImmutableSet.copyOf(certificates);
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getYear() {
        return year;
    }

    public static final Function<Content, List<Clip>> TO_CLIPS = new Function<Content, List<Clip>>() {
        @Override
        public List<Clip> apply(Content input) {
            return input.getClips();
        }
    };

    public Integer getEditorialPriority() {
        return editorialPriority;
    }

    public void setEditorialPriority(Integer editorialPriority) {
        this.editorialPriority = editorialPriority;
    }
}
