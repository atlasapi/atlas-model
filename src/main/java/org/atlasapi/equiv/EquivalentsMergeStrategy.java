package org.atlasapi.equiv;

import org.atlasapi.application.ApplicationConfiguration;

/**
 *  Merges a set of equivalents into a single chosen resource.
 *
 * @param <E>
 */
public interface EquivalentsMergeStrategy<E extends Equivalent<E>> {

    /**
     * @param chosen - resource in to which {@code equivalents} is merged.
     * @param equivalents - a set of resources equivalent to chosen.
     * @return merged resources.
     */
    <T extends E> T merge(T chosen, Iterable<T> equivalents, ApplicationConfiguration config);
    
}
