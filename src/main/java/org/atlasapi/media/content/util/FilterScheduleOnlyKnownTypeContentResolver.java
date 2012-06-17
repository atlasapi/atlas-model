package org.atlasapi.media.content.util;

import org.atlasapi.media.content.LookupRef;
import org.atlasapi.media.content.ResolvedContent;

import com.google.common.base.Predicates;

public class FilterScheduleOnlyKnownTypeContentResolver implements KnownTypeContentResolver {
    
    private final KnownTypeContentResolver resolver;

    public FilterScheduleOnlyKnownTypeContentResolver(KnownTypeContentResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public ResolvedContent findByLookupRefs(Iterable<LookupRef> lookupRefs) {
        ResolvedContent resolvedContent = resolver.findByLookupRefs(lookupRefs);
        return resolvedContent.filterContent(Predicates.not(FilterScheduleOnlyContentResolver.SCHEDULE_ONLY));
    }
}
