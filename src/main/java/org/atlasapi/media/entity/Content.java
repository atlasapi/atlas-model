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
    private ImmutableList<Clip> clips = ImmutableList.of();
    private Set<KeyPhrase> keyPhrases = ImmutableSet.of();
    private Set<RelatedLink> relatedLinks = ImmutableSet.of();
    private ImmutableList<TopicRef> topicRefs = ImmutableList.of();
    private ImmutableList<ContentGroupRef> contentGroupRefs = ImmutableList.of();

    public Content(String uri, String curie, Publisher publisher) {
        super(uri, curie, publisher);
    }

    public Content() { /*
         * some legacy code still requires a default constructor
         */

    }

    public List<Clip> getClips() {
        return clips;
    }

    public void setTopicRefs(Iterable<TopicRef> topicRefs) {
        this.topicRefs = ImmutableList.copyOf(topicRefs);
    }

    public void addTopicRef(TopicRef topicRef) {
        topicRefs = ImmutableList.<TopicRef>builder().add(topicRef).addAll(topicRefs).build();
    }

    public List<TopicRef> getTopicRefs() {
        return topicRefs;
    }

    public void setContentGroupRefs(Iterable<ContentGroupRef> contentGroupRefs) {
        this.contentGroupRefs = ImmutableList.copyOf(contentGroupRefs);
    }

    public void addContentGroupRef(ContentGroupRef contentGroupRef) {
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

    public Set<KeyPhrase> getKeyPhrases() {
        return keyPhrases;
    }

    public void setKeyPhrases(Iterable<KeyPhrase> phrases) {
        keyPhrases = ImmutableSet.copyOf(phrases);
    }

    public void addKeyPhrase(KeyPhrase phrase) {
        keyPhrases = ImmutableSet.<KeyPhrase>builder().add(phrase).addAll(keyPhrases).build();
    }

    public Set<RelatedLink> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(Iterable<RelatedLink> links) {
        relatedLinks = ImmutableSet.copyOf(links);
    }

    public void addRelatedLink(RelatedLink link) {
        relatedLinks = ImmutableSet.<RelatedLink>builder().add(link).addAll(relatedLinks).build();
    }

    public static void copyTo(Content from, Content to) {
        Described.copyTo(from, to);
        to.clips = ImmutableList.copyOf(Iterables.transform(from.clips, Clip.COPIES));
        to.keyPhrases = from.keyPhrases;
        to.relatedLinks = from.relatedLinks;
        to.topicRefs = from.topicRefs;
        to.readHash = from.readHash;
    }

    public void setReadHash(String readHash) {
        this.readHash = readHash;
    }

    public boolean hashChanged(String newHash) {
        return readHash == null || !this.readHash.equals(newHash);
    }
}
