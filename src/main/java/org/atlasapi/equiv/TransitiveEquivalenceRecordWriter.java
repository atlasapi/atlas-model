package org.atlasapi.equiv;

import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.transform;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Sourced;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Identifiables;
import org.atlasapi.media.util.Sourceds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Functions;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.metabroadcast.common.collect.OptionalMap;

public class TransitiveEquivalenceRecordWriter implements EquivalenceRecordWriter {

    public static EquivalenceRecordWriter explicit(EquivalenceRecordStore store) {
        return new TransitiveEquivalenceRecordWriter(store, true);
    }

    public static EquivalenceRecordWriter generated(EquivalenceRecordStore store) {
        return new TransitiveEquivalenceRecordWriter(store, false);
    }

    private static final Logger log = LoggerFactory.getLogger(TransitiveEquivalenceRecordWriter.class);
    private static final int maxSetSize = 150;

    private final EquivalenceRecordStore store;
    private final boolean explicit;
    
    private TransitiveEquivalenceRecordWriter(EquivalenceRecordStore store, boolean explicit) {
        this.store = store;
        this.explicit = explicit;
    }

    @Override
    public void writeLookup(ContentRef subj, Iterable<ContentRef> equivRefs,
            Set<Publisher> publishers) {
        
        Preconditions.checkNotNull(subj, "null subject");
        final ImmutableSet<Publisher> sources = ImmutableSet.copyOf(publishers);
        Predicate<Sourced> sourceFilter = Sourceds.sourceFilter(sources);
        ImmutableSet<ContentRef> newAdjacents = ImmutableSet.copyOf(
            Iterables.filter(equivRefs, sourceFilter));
        Id subjId = subj.getId();
        
        //entry for the subject, read alone first to avoid reading the entire
        //existing transitive set until it's definitely necessary.
        EquivalenceRecord subject
            = store.resolveRecords(ImmutableList.of(subjId)).get(subjId)
                .or(EquivalenceRecord.valueOf(subj));
        
        Set<EquivalenceRef> currentAdjacents
            = Sets.filter(relevantAdjacents(subject), sourceFilter);
        
        if(noChangeInAdjacents(subj, newAdjacents, currentAdjacents)) {
            return;
        }
        
        final Set<EquivalenceRecord> adjacent = recordsFor(newAdjacents);

        Map<Id, EquivalenceRecord> transitiveSet = transitiveClosure(subject, adjacent);
        
        if(transitiveSet.size() > maxSetSize) {
            log.info("Transitive set too large: {} {}", subj, transitiveSet.size());
            return;
        }
        
        final ImmutableSet<EquivalenceRef> subjectRef = ImmutableSet.of(subject.getSelf());
        for (EquivalenceRecord record : transitiveSet.values()) {
            Iterable<EquivalenceRef> updatedAdjacents;
            if (record.equals(subject)) {
                updatedAdjacents
                    = updateSubjectAdjacent(subject, adjacent, sourceFilter);
            } else if (sources.contains(record.getPublisher())) {
                updatedAdjacents
                    = updateTransitiveAdjacent(record, adjacent, subjectRef);
            } else {
                updatedAdjacents = relevantAdjacents(record);
            }
            record = explicit ? record.copyWithExplicitAdjacent(updatedAdjacents) 
                              : record.copyWithGeneratedAdjacent(updatedAdjacents);
            transitiveSet.put(record.getId(), record);
        }
        
        store.writeRecords(recomputeTransitiveClosures(transitiveSet));

    }

    private Set<EquivalenceRef> updateTransitiveAdjacent(EquivalenceRecord record,
            final Set<EquivalenceRecord> adjacent, final ImmutableSet<EquivalenceRef> subjectRef) {
        Set<EquivalenceRef> relevantEquivs = relevantAdjacents(record);
        return adjacent.contains(record) ? Sets.union(relevantEquivs, subjectRef)
                                         : Sets.difference(relevantEquivs, subjectRef);
    }

    private boolean noChangeInAdjacents(ContentRef subj, ImmutableSet<ContentRef> equivalents,
            Set<EquivalenceRef> relevantEquivalents) {
        Iterable<Id> equivalentIds = Iterables.transform(equivalents, Identifiables.toId());
        ImmutableSet<Id> currentEquivalents = ImmutableSet.copyOf(transform(relevantEquivalents,Identifiables.toId()));
        Set<Id> allIds = ImmutableSet.<Id>builder().add(subj.getId()).addAll(equivalentIds).build();
        boolean noChange = currentEquivalents.equals(allIds);
        if (!noChange) {
            log.trace("Equivalence change: {} -> {}", currentEquivalents, allIds);
        }
        return noChange;
    }

    //keep current refs from other sources, add new adjacent
    private Iterable<EquivalenceRef> updateSubjectAdjacent(EquivalenceRecord subject,
            Set<EquivalenceRecord> adjacent, final Predicate<Sourced> sourceFilter) {
        return Iterables.concat(
            Iterables.filter(relevantAdjacents(subject), Predicates.not(sourceFilter)), 
            Iterables.transform(adjacent, EquivalenceRecord.toSelf())
        );
    }

    private Set<EquivalenceRef> relevantAdjacents(EquivalenceRecord subjectEntry) {
        return explicit ? subjectEntry.getExplicitAdjacents()
                        : subjectEntry.getGeneratedAdjacents();
    }

    private Set<EquivalenceRecord> recomputeTransitiveClosures(Map<Id, EquivalenceRecord> records) {
        Set<EquivalenceRecord> updatedRecords = Sets.newHashSet();
        
        for (EquivalenceRecord record : records.values()) {
            if (!updatedRecords.contains(record)) {
                Set<Id> transitiveSet = transitiveSet(record, records);
                Set<EquivalenceRef> transitiveRefs = transformToRefs(transitiveSet, records);
                for (EquivalenceRef ref : transitiveRefs) {
                    updatedRecords.add(records.get(ref.getId()).copyWithEquivalents(transitiveRefs));
                }
            }
        }
        
        return updatedRecords;
    }

    private ImmutableSet<EquivalenceRef> transformToRefs(Set<Id> transitiveSet,
            Map<Id, EquivalenceRecord> records) {
        return ImmutableSet.copyOf(Iterables.transform(transitiveSet, Functions.compose(EquivalenceRecord.toSelf(), Functions.forMap(records))));
    }
    
    private Set<Id> transitiveSet(EquivalenceRecord record, 
            Map<Id, EquivalenceRecord> index) {
        Set<Id> transitiveSet = Sets.newHashSet();
        Queue<Id> direct = Lists.newLinkedList(neighbours(record));
        //Traverse equivalence graph breadth-first
        while(!direct.isEmpty()) {
            Id current = direct.poll();
            transitiveSet.add(current);
            Set<Id> neighbours = neighbours(index.get(current));
            Iterables.addAll(direct, Iterables.filter(neighbours, not(in(transitiveSet))));
        }
        return transitiveSet;
    }
      
    private Set<Id> neighbours(EquivalenceRecord current) {
        return ImmutableSet.copyOf(Iterables.transform(Iterables.concat(
            current.getGeneratedAdjacents(), current.getExplicitAdjacents()
        ), Identifiables.toId()));
    }
    
    private Set<EquivalenceRecord> recordsFor(Iterable<ContentRef> refs) {
        Iterable<Id> ids = Iterables.transform(refs,Identifiables.toId());
        OptionalMap<Id, EquivalenceRecord> resolved = store.resolveRecords(ids);
        ImmutableSet.Builder<EquivalenceRecord> records = ImmutableSet.builder();
        for (ContentRef ref : refs) {
            records.add(resolved.get(ref.getId()).or(EquivalenceRecord.valueOf(ref)));
        }
        return records.build();
    }
        
    private Map<Id, EquivalenceRecord> transitiveClosure(EquivalenceRecord subject, Set<EquivalenceRecord> records) {
        
        Map<Id, EquivalenceRecord> closure = Maps.newHashMap();
        
        Set<Id> ids = Sets.newHashSet();
        
        closure.put(subject.getId(), subject);
        ids.addAll(Collections2.transform(subject.getEquivalents(), Identifiables.toId()));
        for (EquivalenceRecord record : records) {
            closure.put(record.getId(), record);
            ids.addAll(Collections2.transform(record.getEquivalents(), Identifiables.toId()));
        }
        
        Collection<Optional<EquivalenceRecord>> resolved = store.resolveRecords(ids).values();
        for (Optional<EquivalenceRecord> record : resolved) {
            closure.put(record.get().getId(), record.get());
        }
        
        List<EquivalenceRecord> refList = Lists.newArrayListWithExpectedSize(closure.size());
        Iterables.addAll(refList, closure.values());

        log.trace("READ transitive set: {}", refList);
        return closure;
    }
}
