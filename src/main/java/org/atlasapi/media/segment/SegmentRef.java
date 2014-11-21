package org.atlasapi.media.segment;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

public class SegmentRef {
    
    public static final Function<SegmentRef, Long> TO_ID = new Function<SegmentRef, Long>(){
        @Override
        public Long apply(SegmentRef input) {
            return input.identifier();
        }
    };
    
    private final Long segmentId;

    public SegmentRef(Long segmentId) {
        this.segmentId = checkNotNull(segmentId);
    }

    public Long identifier() {
        return segmentId;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof SegmentRef) {
            SegmentRef other = (SegmentRef) that;
            return other.segmentId.equals(this.segmentId);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return segmentId.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("SegRef %s", segmentId);
    }
}