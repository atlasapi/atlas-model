package org.atlasapi.persistence.lookup;

import java.util.Map;

import org.atlasapi.persistence.lookup.entry.LookupEntry;
import org.atlasapi.persistence.lookup.entry.LookupEntryStore;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

public class InMemoryLookupEntryStore implements LookupEntryStore {

    private Map<String,LookupEntry> uriStore;
    private Map<Long,LookupEntry> idStore;

    public InMemoryLookupEntryStore() {
        uriStore = Maps.newConcurrentMap();
        idStore = Maps.newConcurrentMap();
    }
    
    @Override
    public void store(LookupEntry entry) {
        uriStore.putAll(Maps.uniqueIndex(entry.entriesForIdentifiers(), new Function<LookupEntry, String>() {
            @Override
            public String apply(LookupEntry input) {
                return input.uri();
            }
        }));
        if (entry.id() != null) {
            idStore.put(entry.id(), entry);
        }
    }
    
    @Override
    public Iterable<LookupEntry> entriesForUris(Iterable<String> uris) {
        return Iterables.filter(Iterables.transform(uris, Functions.forMap(uriStore, null)), Predicates.notNull());
    }

    @Override
    public Iterable<LookupEntry> entriesForIds(Iterable<Long> ids) {
        return Iterables.filter(Iterables.transform(ids, Functions.forMap(idStore, null)), Predicates.notNull());
    }
}
