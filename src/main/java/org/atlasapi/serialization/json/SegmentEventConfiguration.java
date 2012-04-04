package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.content.item.SegmentEvent;

/**
 */
@JsonDeserialize(builder=SegmentEvent.Builder.class)
public interface SegmentEventConfiguration {
    
}
