package org.atlasapi.persistence.content;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;
import org.atlasapi.output.Annotation;

/**
 * A content resolver which returns the equivalent set for each requested
 * identifiers inside the given set of sources.
 * 
 */
public interface EquivalentContentResolver {

    /**
     * Resolves the equivalent sets of content for a given set of source URIs.
     * Only content from the given sources is resolved.
     * 
     * @param uris
     *            - requested keys of equivalent content.
     * @param selectedSources
     *            - sources of the equivalent set to resolve.
     * @param activeAnnotations
     *            - components of the model to resolve.
     * @return EquivalentContent with an entry for each of the requested URIs.
     */
    EquivalentContent resolve(Iterable<String> uris, Set<Publisher> selectedSources, Set<Annotation> activeAnnotations);

}
