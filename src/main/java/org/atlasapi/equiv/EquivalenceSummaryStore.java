package org.atlasapi.equiv;

import java.util.Set;

import com.metabroadcast.common.collect.OptionalMap;

public interface EquivalenceSummaryStore {

    void store(EquivalenceSummary summary);
    
    OptionalMap<String, EquivalenceSummary> summariesForUris(Iterable<String> uris);

    Set<EquivalenceSummary> summariesForChildren(String parent);
    
}
