package org.atlasapi.media.segment;

import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.ids.IdGenerator;

public class IdSettingSegmentWriter implements SegmentWriter {

    private final SegmentWriter delegate;
    private final IdGenerator generator;
    private final SegmentResolver resolver;

    public IdSettingSegmentWriter(SegmentWriter delegate, SegmentResolver resolver, IdGenerator generator) {
        this.delegate = delegate;
        this.resolver = resolver;
        this.generator = generator;
    }
    
    @Override
    public Segment write(Segment segment) {
        Maybe<Segment> possibleExistingSegment = resolver.resolveForSource(segment.getPublisher(), segment.getCanonicalUri());
        
        if(possibleExistingSegment.hasValue()) {
            segment.setIdentifier(possibleExistingSegment.requireValue().getIdentifier());
        } else {
            segment.setIdentifier(generator.generate());
        }

        return delegate.write(segment);
    }

}