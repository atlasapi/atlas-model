package org.atlasapi.media.content.util;

import org.atlasapi.media.content.ContentResolver;
import org.atlasapi.media.content.LookupRef;
import org.atlasapi.media.content.ResolvedContent;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;

import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class LookupResolvingContentResolver implements ContentResolver {

    private final KnownTypeContentResolver knownTypeResolver;
    private final LookupEntryStore lookupResolver;

    public LookupResolvingContentResolver(KnownTypeContentResolver knownTypeResolver, LookupEntryStore mongoLookupEntryStore) {
        this.knownTypeResolver = knownTypeResolver;
        this.lookupResolver = mongoLookupEntryStore;
    }
    
    @Override
    public ResolvedContent findByCanonicalUris(Iterable<String> canonicalUris) {
        Iterable<LookupRef> lookupRefs = Iterables.transform(lookupResolver.entriesForUris(canonicalUris), LookupEntry.TO_SELF);
        ImmutableSet<LookupRef> resolvedLookups = ImmutableSet.copyOf(Iterables.filter(lookupRefs, Predicates.notNull()));
        ResolvedContent resolvedContent = knownTypeResolver.findByLookupRefs(resolvedLookups);
        return resolvedContent.copyWithAllRequestedUris(canonicalUris);
    }
}

