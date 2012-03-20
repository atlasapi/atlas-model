package org.atlasapi.persistence.lookup.entry;

public interface LookupEntryStore {

    /**
     * Stores specified entry, and all related entries (like those for aliases).
     * @param entry
     */
    void store(LookupEntry entry);
    
    Iterable<LookupEntry> entriesForUris(Iterable<String> uris);
    
    Iterable<LookupEntry> entriesForIds(Iterable<Long> ids);
    
}
