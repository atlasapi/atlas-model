package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Identified;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.metabroadcast.common.base.Maybe;

public class FilterScheduleOnlyContentResolver implements ContentResolver {

    private final ContentResolver contentResovler;

    public FilterScheduleOnlyContentResolver(ContentResolver contentResovler) {
        this.contentResovler = contentResovler;
    }

    @Override
    public ResolvedContent findByCanonicalUris(Iterable<String> lookups) {
        ResolvedContent resolvedContent = contentResovler.findByCanonicalUris(lookups);
        return resolvedContent.filterContent(Predicates.not(SCHEDULE_ONLY));
    }
    
    public final static Predicate<Maybe<Identified>> SCHEDULE_ONLY = new Predicate<Maybe<Identified>>() {
        @Override
        public boolean apply(Maybe<Identified> input) {
            return input.hasValue() && input.requireValue() instanceof Described && ((Described) input.requireValue()).isScheduleOnly();
        }
    };
}
