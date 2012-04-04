package org.atlasapi.media.content.item;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.media.content.MediaType;
import org.atlasapi.media.content.Specialization;
import org.atlasapi.media.vocabulary.PO;
import org.joda.time.Duration;

@RdfClass(namespace = PO.NS)
public final class Song extends Item {
    
    public static final Builder builder() {
        return new Builder();
    }
    
    public static final class Builder extends Item.BaseBuilder<Song, Builder>{

        private String isrc;
        private Long duration;

        @Override
        public Builder copy(Song t) {
            super.copy(t);
            this.isrc = t.isrc;
            this.duration = t.duration;
            return this;
        }
        
        public Builder withIsrc(String isrc) {
            this.isrc = isrc;
            return builder();
        }

        public Builder withDuration(Long duration) {
            this.duration = duration;
            return builder();
        }
        
        public Builder withDuration(Duration duration) {
            return withDuration(duration.getMillis());
        }
        
        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Song build() {
            return new Song(this);
        }
        
    }

	private final String isrc;
    private final Long duration;
	
    public Song(Builder builder) {
        super(builder.withMediaType(MediaType.AUDIO)
            .withSpecialization(Specialization.MUSIC));
        this.isrc = builder.isrc;
        this.duration = builder.duration;
    }
    
    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }

    public String isrc() {
        return isrc;
    }

    public Duration duration() {
        return duration != null ? Duration.standardSeconds(duration) : null;
    }
}
