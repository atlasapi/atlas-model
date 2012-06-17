package org.atlasapi.media.content.util;

import java.util.Map;

import org.atlasapi.media.content.Identified;
import org.atlasapi.media.content.LookupRef;
import org.atlasapi.media.content.ResolvedContent;
import org.atlasapi.media.content.ResolvedContent.ResolvedContentBuilder;

import com.google.common.collect.Maps;

public class DummyKnownTypeContentResolver implements KnownTypeContentResolver {
    
    private final Map<String, Identified> content = Maps.newHashMap();
    
    @Override
    public ResolvedContent findByLookupRefs(Iterable<LookupRef> lookupRefs) {
        ResolvedContentBuilder results = new ResolvedContentBuilder();
        
        for (LookupRef ref : lookupRefs) {
            results.put(ref.id(), content.get(ref.id()));
        }
        
        return results.build();
    }
    
    public DummyKnownTypeContentResolver respondTo(Iterable<? extends Identified> content) {
        for (Identified item : content) {
            this.content.put(item.getCanonicalUri(), item);
        }
        return this;
    }
}
