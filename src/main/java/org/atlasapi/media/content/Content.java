package org.atlasapi.media.content;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.content.group.ContentGroup.ContentGroupIdentifier;
import org.atlasapi.media.content.item.Clip;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;

/**
 * Base type for audio-visual metadata.
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public abstract class Content extends Identified implements Described {

    public abstract static class Builder<T extends Content, B extends Builder<T, B>> extends Identified.Builder<T, B> {

        protected Description description;
        protected DateTime thisOrChildLastUpdated;
        protected boolean scheduleOnly = false;
        
        protected MediaType mediaType = MediaType.VIDEO;
        protected Specialization specialization;
        
        protected ImmutableSet<String> genres = ImmutableSet.of();
        protected ImmutableSet<KeyPhrase> keyPhrases = ImmutableSet.of();
        protected ImmutableSet<RelatedLink> relatedLinks = ImmutableSet.of();
        protected ImmutableSet<TopicRef> topicRefs = ImmutableSet.of();
        protected ImmutableSet<Clip> clips = ImmutableSet.of();
        protected ImmutableSet<ContentGroupIdentifier> contentGroupRefs = ImmutableSet.of();
        
        @Override
        public B copy(T content) {
            return super.copy(content)
                    .withGenres(content.genres)
                    .withDescription(content.description)
                    .withThisOrChildLastUpdated(content.thisOrChildLastUpdated)
                    .withMediaType(content.mediaType)
                    .withSpecialization(content.specialization)
                    .withScheduleOnly(content.scheduleOnly)
                    .withKeyPhrases(content.keyPhrases)
                    .withRelatedLinks(content.relatedLinks)
                    .withClips(content.clips)
                    .withContentGroupsRefs(content.contentGroupRefs);
        }

        public B withDescription(Description description) {
            this.description = description;
            return builder();
        }

        public B withDescription(Description.Builder descriptionBuilder) {
            return withDescription(descriptionBuilder.build());
        }
        
        public B withGenres(Iterable<String> genres) {
            this.genres = ImmutableSet.copyOf(genres);
            return builder();
        }
        
        public B withMediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return builder();
        }
        
        public B withSpecialization(Specialization specialization) {
            this.specialization = specialization;
            return builder();
        }
        
        public B withThisOrChildLastUpdated(DateTime updated) {
            this.thisOrChildLastUpdated = updated;
            return builder();
        }
        
        public B withScheduleOnly(boolean scheduleOnly) {
            this.scheduleOnly = scheduleOnly;
            return builder();
        }
        
        public B withKeyPhrases(Iterable<KeyPhrase> keyPhrases) {
            this.keyPhrases = ImmutableSet.copyOf(keyPhrases);
            return builder();
        }
        
        public B withRelatedLinks(Iterable<RelatedLink> relatedLinks) {
            this.relatedLinks = ImmutableSet.copyOf(relatedLinks);
            return builder();
        }
        
        public B withTopicRefs(Iterable<TopicRef> topicRefs) {
            this.topicRefs = ImmutableSet.copyOf(topicRefs);
            return builder();
        }
        
        public B withClips(Iterable<Clip> clips) {
            this.clips = ImmutableSet.copyOf(clips);
            return builder();
        }
        
        public B withContentGroupsRefs(Iterable<ContentGroupIdentifier> contentGroupRefs){
            this.contentGroupRefs = ImmutableSet.copyOf(contentGroupRefs);
            return builder();
        }
    }

    protected final ImmutableSet<String> genres;
    protected final Description description;
    protected final MediaType mediaType;
    protected final Specialization specialization;
    protected final DateTime thisOrChildLastUpdated;
    protected final boolean scheduleOnly;
    protected final ImmutableSet<KeyPhrase> keyPhrases;
    protected final ImmutableSet<RelatedLink> relatedLinks;
    protected final ImmutableSet<TopicRef> topicRefs;
    protected final ImmutableSet<Clip> clips;
    protected final ImmutableSet<ContentGroupIdentifier> contentGroupRefs;

    protected Content(Builder<? extends Content, ? extends Builder<? extends Content, ?>> builder) {
        super(builder);
        this.genres = ImmutableSet.copyOf(builder.genres);
        this.description = builder.description;
        this.mediaType = builder.mediaType;
        this.specialization = builder.specialization;
        this.thisOrChildLastUpdated = builder.thisOrChildLastUpdated;
        this.scheduleOnly = builder.scheduleOnly;
        this.keyPhrases = builder.keyPhrases;
        this.relatedLinks = builder.relatedLinks;
        this.topicRefs = builder.topicRefs;
        this.clips = builder.clips;
        this.contentGroupRefs = builder.contentGroupRefs;
    }

    /**
     * @return set of genres associated with this <code>Content</code>
     */
    //Should there be a separate genre type?
    public ImmutableSet<String> genres() {
        return genres;
    }

    /**
     * @return <code>Description</code> of the <code>Content</code>.
     */
    //Should this be a set or maps of descriptions?
    public Description description() {
        return description;
    }

    /**
     * @see MediaType
     * @return <code>MediaType</code> of this <code>Content</code>
     */
    public MediaType mediaType() {
        return mediaType;
    }

    /**
     * Specialization of this <code>Content</code>. Effectively a more specific MediaType.
     * @see MediaType, Specialization
     * @return <code>Specialization</code> of this <code>Content</code>
     */
    public Specialization specialization() {
        return specialization;
    }

    /**
     * The time this or a major constituent part of this <code>Content</code> was updated 
     * @return
     */
    public DateTime thisOrChildLastUpdated() {
        return thisOrChildLastUpdated;
    }

    /**
     * Marks that this <code>Content</code> should only by available as part of
     * a schedule result not as an individual piece of <code>Content</code>.
     * 
     * @return true if this <code>Content</code> should only be in a schedule result, false otherwise.
     */
    public boolean isScheduleOnly() {
        return scheduleOnly;
    }
    
    /**
     * @see KeyPhrase
     * @return set of <code>KeyPhrase</code>s related to this <code>Content</code>.
     */
    public ImmutableSet<KeyPhrase> keyPhrases() {
        return keyPhrases;
    }
    
    /**
     * @see RelatedLink
     * @return set of <code>RelatedLink</code>s related to this <code>Content</code>.
     */
    public ImmutableSet<RelatedLink> relatedLinks() {
        return relatedLinks;
    }

    public ImmutableSet<TopicRef> topicRefs() {
        return topicRefs;
    }

    public ImmutableSet<Clip> clips() {
        return clips;
    }
    
    public ImmutableSet<ContentGroupIdentifier> contentGroupRefs() {
        return contentGroupRefs;
    }
}
