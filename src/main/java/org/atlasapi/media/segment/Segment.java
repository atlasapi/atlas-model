package org.atlasapi.media.segment;


import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;
import org.joda.time.Duration;

public final class Segment extends Identified implements Described {

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Identified.Builder<Segment, Builder> {

        private SegmentType type;
        private Description description = Description.EMPTY;
        private Duration duration;

        @Override
        public Builder copy(Segment segment) {
            super.copy(segment);
            this.description = segment.description;
            this.type = segment.type;
            this.duration = segment.duration;
            return builder();
        }
        
        public Builder withtype(SegmentType type) {
            this.type = type;
            return builder();
        }

        public Builder withdescription(Description description) {
            this.description = description;
            return builder();
        }

        public Builder withduration(Duration duration) {
            this.duration = duration;
            return builder();
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Segment build() {
            return new Segment(this);
        }

    }

    private final SegmentType type;
    private final Description description;
    private final Duration duration;

    public Segment(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.description = builder.description;
        this.duration = builder.duration;
    }
    
    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }

    public SegmentIdentifier toIdentifier() {
        return new SegmentIdentifier(this);
    }
    
    public static final class SegmentIdentifier extends Identifier {

        public SegmentIdentifier(Segment segment) {
            super(segment);
        }
        
    }

    public Description description() {
        return this.description;
    }

    public SegmentType getType() {
        return this.type;
    }

    public Duration getDuration() {
        return this.duration;
    }

}
