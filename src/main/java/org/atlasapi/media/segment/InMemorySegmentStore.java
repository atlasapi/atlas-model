package org.atlasapi.media.segment;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.atlasapi.media.content.Publisher;

import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.metabroadcast.common.base.Maybe;

public class InMemorySegmentStore implements SegmentResolver, SegmentWriter {

    private ConcurrentMap<SegmentRef, Segment> idStore;
    private Table<Publisher, String, Segment> sourceStore;

    public InMemorySegmentStore() {
        this.idStore = Maps.newConcurrentMap();
        this.sourceStore = Tables.newCustomTable(Maps.<Publisher,Map<String,Segment>>newConcurrentMap(), new Supplier<Map<String, Segment>>(){

            @Override
            public Map<String, Segment> get() {
                return Maps.newConcurrentMap();
            }});
    }
    
    @Override
    public Maybe<Segment> resolveForSource(Publisher source, String sourceId) {
        return Maybe.fromPossibleNullValue(sourceStore.get(source, sourceId));
    }

    @Override
    public Segment write(Segment segment) {
        idStore.put(segment.toRef(), segment);
        sourceStore.put(segment.getPublisher(), segment.getCanonicalUri(), segment);
        return segment;
    }

    @Override
    public Map<SegmentRef, Maybe<Segment>> resolveById(Iterable<SegmentRef> identifiers) {
        final Builder<SegmentRef, Maybe<Segment>> resolved = ImmutableMap.builder();
        for (SegmentRef segmentRef : identifiers) {
            resolved.put(segmentRef, Maybe.fromPossibleNullValue(idStore.get(segmentRef)));
        }
        return resolved.build();
    }

}
