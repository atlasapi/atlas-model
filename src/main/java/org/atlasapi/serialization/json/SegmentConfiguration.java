package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.segment.Segment;

/**
 */
@JsonDeserialize(builder=Segment.Builder.class)
public interface SegmentConfiguration {
    
}
