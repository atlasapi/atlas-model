package org.atlasapi.media.segment;

import java.util.Map;

import org.atlasapi.media.content.Publisher;

import com.metabroadcast.common.base.Maybe;

public interface SegmentResolver {

    Map<SegmentRef,Maybe<Segment>> resolveById(Iterable<SegmentRef> identifier);
    
    Maybe<Segment> resolveForSource(Publisher source, String sourceId); 
    
}
