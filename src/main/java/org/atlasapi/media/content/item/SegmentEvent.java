package org.atlasapi.media.content.item;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.atlasapi.media.segment.Segment.SegmentIdentifier;
import org.joda.time.Duration;

public final class SegmentEvent extends Identified implements Described {

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Identified.Builder<SegmentEvent, Builder> {

        private Integer position;
        private Duration offset;
        private Boolean isChapter;
        private Description description;
        private SegmentIdentifier segment;

        @Override
        public Builder copy(SegmentEvent builder) {
            super.copy(builder);
            this.position = builder.position;
            this.offset = builder.offset;
            this.isChapter = builder.isChapter;
            this.description = builder.description;
            this.segment = builder.segment;
            return builder();
        }

        public Builder withposition(Integer position) {
            this.position = position;
            return builder();
        }

        public Builder withoffset(Duration offset) {
            this.offset = offset;
            return builder();
        }

        public Builder withisChapter(Boolean isChapter) {
            this.isChapter = isChapter;
            return builder();
        }

        public Builder withdescription(Description description) {
            this.description = description;
            return builder();
        }

        public Builder withsegment(SegmentIdentifier segment) {
            this.segment = segment;
            return builder();
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public SegmentEvent build() {
            return new SegmentEvent(this);
        }

    }

    private final Integer position;
    private final Duration offset;
    private final Boolean isChapter;

    private final Description description;

    private final SegmentIdentifier segment;

    public SegmentEvent(Builder builder) {
        super(builder);
        this.position = builder.position;
        this.offset = builder.offset;
        this.isChapter = builder.isChapter;
        this.description = builder.description;
        this.segment = builder.segment;

    }

    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }

    public Integer position() {
        return this.position;
    }

    public Duration offset() {
        return this.offset;
    }

    public Boolean isChapter() {
        return this.isChapter;
    }

    public Description description() {
        return this.description;
    }

    public SegmentIdentifier segment() {
        return this.segment;
    }

    @Override
    public SegmentEventIdentifier toIdentifier() {
        return new SegmentEventIdentifier(this);
    }
    
    public static final class SegmentEventIdentifier extends Identifier {

        protected SegmentEventIdentifier(SegmentEvent identified) {
            super(identified);
        }
        
    }
}