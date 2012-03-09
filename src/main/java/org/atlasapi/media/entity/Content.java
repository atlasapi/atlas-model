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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public abstract class Content extends Described {

    private transient String readHash;
    private Clips clips = new Clips();
    private KeyPhrases keyPhrases = new KeyPhrases();
    private RelatedLinks relatedLinks = new RelatedLinks();
    private TopicRefs topicRefs = new TopicRefs();
    private String id;

    public Content(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
    }

    public Content() { /*
         * some legacy code still requires a default constructor
         */

    }

    public List<Clip> getClips() {
        return clips.values;
    }

    public void setTopicRefs(Iterable<TopicRef> topicRefs) {
        this.topicRefs.values = ImmutableList.copyOf(topicRefs);
    }

    public void addTopicRef(TopicRef topicRef) {
        this.topicRefs.values = ImmutableList.<TopicRef>builder().add(topicRef).addAll(topicRefs.values).build();
    }

    public List<TopicRef> getTopicRefs() {
        return topicRefs.values;
    }

    public void setClips(Iterable<Clip> clips) {
        this.clips.values = ImmutableList.copyOf(clips);
        for (Clip clip : clips) {
            clip.setClipOf(this.getCanonicalUri());
        }
    }

    public void addClip(Clip clip) {
        List<Clip> all = Lists.newArrayList(clips.values);
        all.add(clip);
        setClips(all);
    }

    public Set<KeyPhrase> getKeyPhrases() {
        return keyPhrases.values;
    }

    public void setKeyPhrases(Iterable<KeyPhrase> phrases) {
        keyPhrases.values = ImmutableSet.copyOf(phrases);
    }

    public void addKeyPhrase(KeyPhrase phrase) {
        keyPhrases.values = ImmutableSet.<KeyPhrase>builder().add(phrase).addAll(keyPhrases.values).build();
    }

    public Set<RelatedLink> getRelatedLinks() {
        return relatedLinks.values;
    }

    public void setRelatedLinks(Iterable<RelatedLink> links) {
        relatedLinks.values = ImmutableSet.copyOf(links);
    }

    public void addRelatedLink(RelatedLink link) {
        relatedLinks.values = ImmutableSet.<RelatedLink>builder().add(link).addAll(relatedLinks.values).build();
    }

    public static void copyTo(Content from, Content to) {
        Described.copyTo(from, to);
        to.clips.values = ImmutableList.copyOf(Iterables.transform(from.clips.values, Clip.COPIES));
        to.keyPhrases = from.keyPhrases;
        to.relatedLinks = from.relatedLinks;
        to.topicRefs = from.topicRefs;
        to.readHash = from.readHash;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStringId() {
        return id;
    }

    public void setReadHash(String readHash) {
        this.readHash = readHash;
    }

    public boolean hashChanged(String newHash) {
        return readHash == null || !this.readHash.equals(newHash);
    }

    public static class Clips {

        public List<Clip> values = ImmutableList.of();

        public Clips(List<Clip> values) {
            this.values = values;
        }

        public Clips() {
        }
    }

    public static class KeyPhrases {

        public Set<KeyPhrase> values = ImmutableSet.of();

        public KeyPhrases(Set<KeyPhrase> values) {
            this.values = values;
        }

        public KeyPhrases() {
        }
    }

    public static class RelatedLinks {

        public Set<RelatedLink> values = ImmutableSet.of();

        public RelatedLinks(Set<RelatedLink> values) {
            this.values = values;
        }

        public RelatedLinks() {
        }
    }

    public static class TopicRefs {

        public List<TopicRef> values = ImmutableList.of();

        public TopicRefs(List<TopicRef> values) {
            this.values = values;
        }

        public TopicRefs() {
        }
    }
}
