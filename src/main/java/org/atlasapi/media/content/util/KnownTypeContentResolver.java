package org.atlasapi.media.content.util;

import org.atlasapi.media.content.LookupRef;
import org.atlasapi.media.content.ResolvedContent;

/**
 * Retrieve content from a known internal content table
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public interface KnownTypeContentResolver {

    ResolvedContent findByLookupRefs(Iterable<LookupRef> lookupRefs);
    
}
