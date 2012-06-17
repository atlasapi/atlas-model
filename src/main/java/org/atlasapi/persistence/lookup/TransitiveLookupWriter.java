package org.atlasapi.persistence.lookup;

import static com.google.common.base.Predicates.in;
import static com.google.common.base.Predicates.not;
import static com.google.common.collect.Iterables.filter;
import static org.atlasapi.media.content.Identified.TO_URI;

import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.Described;
import org.atlasapi.media.content.Identified;
import org.atlasapi.media.content.LookupRef;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class TransitiveLookupWriter implements LookupWriter {

    private final LookupEntryStore entryStore;
    private final boolean explicit;
    
    public static TransitiveLookupWriter explicitTransitiveLookupWriter(LookupEntryStore entryStore) {
        return new TransitiveLookupWriter(entryStore, true);
    }
    
    public static TransitiveLookupWriter generatedTransitiveLookupWriter(LookupEntryStore entryStore) {
        return new TransitiveLookupWriter(entryStore, false);
    }

    private TransitiveLookupWriter(LookupEntryStore entryStore, boolean explicit) {
        this.entryStore = entryStore;
        this.explicit = explicit;
    }

    @Override
    public <T extends Content> void writeLookup(T subject, Iterable<T> equivalents, Set<Publisher> publishers) {
        writeLookup(subject.getCanonicalUri(), Iterables.transform(filterContentPublishers(equivalents, publishers), TO_URI), publishers);
    }
    
    public void writeLookup(final String subjectUri, Iterable<String> equivalentUris, final Set<Publisher> publishers) {
        
        //canonical URIs of subject and directEquivalents
        final Set<String> canonUris = ImmutableSet.<String>builder().add(subjectUri).addAll(equivalentUris).build();

        //entry for the subject.
        LookupEntry subjectEntry = get(subjectUri);
        
        if(subjectEntry != null && ImmutableSet.copyOf(Iterables.transform(relevantEquivalents(subjectEntry),LookupRef.TO_ID)).equals(canonUris)) {
            return;
        }
        
        Set<LookupEntry> equivEntries = entriesFor(equivalentUris);

        //Pull the current transitive closures for the directly equivalent parameters.
        Map<LookupRef, LookupEntry> lookups = transitiveClosure(ImmutableSet.copyOf(Iterables.concat(ImmutableSet.of(subjectEntry), equivEntries)));
        
        
        final ImmutableSet<LookupRef> subjectRef = ImmutableSet.of(subjectEntry.lookupRef());
        //Update the direct equivalents for all the lookups.
        lookups = Maps.newHashMap(Maps.transformValues(lookups, new Function<LookupEntry, LookupEntry>() {
                @Override
                public LookupEntry apply(LookupEntry entry) {
                    // Only modify direct equivalents of entries in the
                    // transitive closure of publishers that are argued
                    if (!publishers.contains(entry.lookupRef().publisher())) {
                        return entry;
                    }
                    SetView<LookupRef> updatedNeighbours;
                    if (canonUris.contains(entry.uri())) {
                        updatedNeighbours = Sets.union(relevantEquivalents(entry), subjectRef);
                    } else {
                        updatedNeighbours = Sets.difference(relevantEquivalents(entry), subjectRef);
                    }
                    return explicit ? entry.copyWithExplicitEquivalents(updatedNeighbours) 
                                    : entry.copyWithDirectEquivalents(updatedNeighbours);
                }
        }));
        
        /* Update the subject content entry. Included:
         *  refs of publishers not argued.
         *  refs for all content argued as equivalent.
         */
        Iterable<LookupRef> neighbours = neighbours(publishers, subjectEntry, equivEntries);
        lookups.put(subjectEntry.lookupRef(), explicit ? subjectEntry.copyWithExplicitEquivalents(neighbours)
                                                       : subjectEntry.copyWithDirectEquivalents(neighbours));
        
        //For each lookup, recompute its transitive closure. 
        Set<LookupEntry> newLookups = recomputeTransitiveClosures(lookups.values());

        //Write to store
        for (LookupEntry entry : newLookups) {
            entryStore.store(entry);
        }

    }

    private Iterable<LookupRef> neighbours(final Set<Publisher> publishers,
            LookupEntry subjectEntry, Set<LookupEntry> equivEntries) {
        return Iterables.concat(
                retainRefsNotInPublishers(relevantEquivalents(subjectEntry), publishers), 
                Iterables.transform(equivEntries, LookupEntry.TO_SELF)
        );
    }

    private Set<LookupRef> relevantEquivalents(LookupEntry subjectEntry) {
        return explicit ? subjectEntry.explicitEquivalents() : subjectEntry.directEquivalents();
    }

    private Iterable<LookupRef> retainRefsNotInPublishers(Set<LookupRef> directEquivalents, final Set<Publisher> publishers) {
        return Iterables.filter(directEquivalents, new Predicate<LookupRef>() {
            @Override
            public boolean apply(LookupRef input) {
                return !publishers.contains(input.publisher());
            }
        });
    }
    
    private <T extends Described> Iterable<T> filterContentPublishers(Iterable<T> content, final Set<Publisher> publishers) {
        return Iterables.filter(content, new Predicate<Described>() {
            @Override
            public boolean apply(Described input) {
                return publishers.contains(input.getPublisher());
            }
        });
    }

    private Set<LookupEntry> recomputeTransitiveClosures(Iterable<LookupEntry> lookups) {
        
        Map<String,LookupEntry> lookupMap = Maps.uniqueIndex(lookups, LookupEntry.TO_ID);
        
        Set<LookupEntry> newLookups = Sets.newHashSet();
        for (LookupEntry entry : lookups) {
            
            Set<LookupRef> transitiveSet = Sets.newHashSet();
            
            Set<LookupRef> seen = Sets.newHashSet();
            Queue<LookupRef> direct = Lists.newLinkedList(neighbours(entry));
            //Traverse equivalence graph breadth-first
            while(!direct.isEmpty()) {
                LookupRef current = direct.poll();
                if(seen.contains(current)) {
                    continue;
                } else {
                    seen.add(current);
                }
                transitiveSet.add(current);
                direct.addAll(lookupMap.get(current.id()).directEquivalents());
                direct.addAll(lookupMap.get(current.id()).explicitEquivalents());
            }
            
            newLookups.add(entry.copyWithEquivalents(transitiveSet));
        }
        return newLookups;
    }
    
    private Set<LookupEntry> entriesFor(Iterable<String> equivalents) {
        return ImmutableSet.copyOf(entryStore.entriesForUris(equivalents));
    }

    private LookupEntry get(String subject) {
        return Iterables.getOnlyElement(entryStore.entriesForUris(ImmutableList.of(subject)), null);
    }

    // Uses a work queue to pull out and map the transitive closures rooted at each entry in entries.
    private Map<LookupRef, LookupEntry> transitiveClosure(Set<LookupEntry> entries) {
        
        Queue<LookupEntry> toProcess = Lists.newLinkedList(entries);
        
        Map<LookupRef, LookupEntry> found = Maps.newHashMap(Maps.uniqueIndex(entries, LookupEntry.TO_SELF));
        
        while(!toProcess.isEmpty()) {
            LookupEntry current = toProcess.poll();
            found.put(current.lookupRef(), current);
            //add entries for equivalents that haven't been seen before to the work queue
            toProcess.addAll(entriesForRefs(filter(neighbours(current), not(in(found.keySet())))));
        }
        
        return found;
    }

    private Iterable<LookupRef> neighbours(LookupEntry current) {
        return ImmutableSet.copyOf(Iterables.concat(current.directEquivalents(), current.explicitEquivalents()));
    }

    private Set<LookupEntry> entriesForRefs(Iterable<LookupRef> refs) {
        return ImmutableSet.copyOf(entryStore.entriesForUris(Iterables.transform(refs, LookupRef.TO_ID)));
    }
}
