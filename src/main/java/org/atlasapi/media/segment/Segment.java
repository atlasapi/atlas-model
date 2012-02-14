package org.atlasapi.media.segment;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.SegmentType;
import org.atlasapi.media.entity.Description;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.Duration;

import com.google.common.base.Function;

public class Segment extends Identified {

    private Description description = Description.EMPTY;
    private SegmentType type;
    private Duration duration;
    private Publisher publisher;
    private String identifier;
    
    public SegmentRef toRef() {
        return new SegmentRef(checkNotNull(identifier, "Can't create reference for segment without ID"));
    }

    public Description getDescription() {
        return this.description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public SegmentType getType() {
        return this.type;
    }

    public void setType(SegmentType type) {
        this.type = type;
    }

    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public static final Function<Segment, SegmentRef> TO_REF = new Function<Segment, SegmentRef>() {
        @Override
        public SegmentRef apply(Segment input) {
            return input.toRef();
        }
    };
    
}
