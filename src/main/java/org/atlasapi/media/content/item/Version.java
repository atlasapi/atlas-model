package org.atlasapi.media.content.item;

import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

import com.google.common.collect.ImmutableSet;

public final class Version extends Identified {
    
    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Identified.Builder<Version, Builder> {

        private ImmutableSet<Encoding> encoding = ImmutableSet.of();
        private ImmutableSet<Broadcast> broadcasts = ImmutableSet.of();
        private ImmutableSet<SegmentEvent> segmentEvents = ImmutableSet.of();

        private Restriction restriction;

        private Integer publishedDuration;
        private Integer duration;
        
        @Override
        public Builder copy(Version version) {
            super.copy(version);
            this.encoding = version.encoding;
            this.broadcasts = version.broadcasts;
            this.segmentEvents = version.segmentEvents;
            this.restriction = version.restriction;
            this.publishedDuration = version.publishedDuration;
            this.duration = version.duration;
            return builder();
        }

        public Builder withEncoding(Iterable<Encoding> encoding) {
            this.encoding = ImmutableSet.copyOf(encoding);
            return builder();
        }

        public Builder withBroadcasts(Iterable<Broadcast> broadcasts) {
            this.broadcasts = ImmutableSet.copyOf(broadcasts);
            return builder();
        }
        
        public Builder withSegmentEvents(Iterable<SegmentEvent> segmentEvents) {
            this.segmentEvents = ImmutableSet.copyOf(segmentEvents);
            return builder();
        }

        public Builder withRestriction(Restriction restriction) {
            this.restriction = restriction;
            return builder();
        }

        public Builder withPublishedDuration(Integer publishedDuration) {
            this.publishedDuration = publishedDuration;
            return builder();
        }

        public Builder withDuration(Integer duration) {
            this.duration = duration;
            return builder();
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Version build() {
            return new Version(this);
        }
        
    }

    private final ImmutableSet<Encoding> encoding;
    private final ImmutableSet<Broadcast> broadcasts;
    private final ImmutableSet<SegmentEvent> segmentEvents;

    private final Restriction restriction;

    private final Integer publishedDuration;
    private final Integer duration;

    public Version(Builder builder) {
        super(builder);
        this.encoding = builder.encoding;
        this.broadcasts = builder.broadcasts;
        this.segmentEvents = builder.segmentEvents;
        this.restriction = builder.restriction;
        this.publishedDuration = builder.publishedDuration;
        this.duration = builder.duration;
    }

    public Builder copy() {
        return null;
    }
    
    public ImmutableSet<Encoding> encodings() {
        return encoding;
    }

    public ImmutableSet<Broadcast> broadcasts() {
        return broadcasts;
    }
    
    public ImmutableSet<SegmentEvent> segmentEvents() {
        return segmentEvents;
    }

    public Integer publishedDuration() {
        return this.publishedDuration;
    }

    public Integer duration() {
        return this.duration;
    }

    public Restriction restriction() {
        return restriction;
    }
    
    @Override
    public Identifier toIdentifier() {
        return new VersionIdentifier(this);
    }
    
    public static final class VersionIdentifier extends Identifier {

        protected VersionIdentifier(Identified identified) {
            super(identified);
        }
        
    }
}
