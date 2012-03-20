package org.atlasapi.persistence.content;

import org.atlasapi.persistence.content.ResolvedContent;
import org.atlasapi.persistence.lookup.entry.LookupRef;

/**
 * Retrieve content from a known internal content table
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public interface KnownTypeContentResolver {

    ResolvedContent findByLookupRefs(Iterable<LookupRef> lookupRefs);
    
}
