package org.atlasapi.equiv;

import com.metabroadcast.common.collect.OptionalMap;

public interface EquivalenceSummaryStore {

    void store(EquivalenceSummary summary);
    
    OptionalMap<String, EquivalenceSummary> summariesForUris(Iterable<String> uris);

}
