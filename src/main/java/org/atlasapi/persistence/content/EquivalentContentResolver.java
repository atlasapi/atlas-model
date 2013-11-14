package org.atlasapi.persistence.content;

import java.util.Set;

import org.atlasapi.application.v3.ApplicationConfiguration;
import org.atlasapi.output.Annotation;

import com.google.common.base.Optional;

/**
 * A content resolver which returns the equivalent set for each requested
 * identifiers inside the given set of sources.
 * 
 */
public interface EquivalentContentResolver {

    /**
     * Resolves the equivalent sets of content for a given set of source URIs.
     * Only content from enabled sources in the app config is resolved.
     * 
     * @param uris
     *            - requested URI keys of equivalent content.
     * @param appConfig
     *            - configuration used to filter content.
     * @param activeAnnotations
     *            - components of the model to resolve.
     * @param withAliases 
     *            - whether aliases should be resolved or not.
     * @return EquivalentContent with an entry for each of the requested URIs.
     */
    EquivalentContent resolveUris(Iterable<String> uris, ApplicationConfiguration appConfig, Set<Annotation> activeAnnotations, boolean withAliases);
    
    /**
     * Resolves the equivalent sets of content for a given set of source URIs.
     * Only content from enabled sources in the app config is resolved.
     * 
     * @param ids
     *            - requested numeric keys of equivalent content.
     * @param appConfig
     *            - configuration used to filter content.
     * @param activeAnnotations
     *            - components of the model to resolve.
     * @return EquivalentContent with an entry for each of the requested URIs.
     */
    EquivalentContent resolveIds(Iterable<Long> ids, ApplicationConfiguration appConfig, Set<Annotation> activeAnnotations);
    
    /**
     * Resolves the equivalent sets of content for a possible namespace and
     * alias values. Only content from enabled sources in the app config is resolved.
     * 
     * @param ids
     *            - requested numeric keys of equivalent content.
     * @param appConfig
     *            - configuration used to filter content.
     * @param activeAnnotations
     *            - components of the model to resolve.
     * @return EquivalentContent with an entry for each of the requested URIs.
     */
    EquivalentContent resolveAliases(Optional<String> namespace, Iterable<String> values, ApplicationConfiguration appConfig, Set<Annotation> activeAnnotations);

}