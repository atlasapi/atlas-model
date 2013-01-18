package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.media.common.Id;

import com.metabroadcast.common.collect.OptionalMap;

public interface EquivalenceSummaryStore {

    void store(EquivalenceSummary summary);
    
    OptionalMap<Id, EquivalenceSummary> summariesForIds(Iterable<Id> ids);

    Set<EquivalenceSummary> summariesForChildren(Id parent);

}
