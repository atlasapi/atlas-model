package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

public interface EquivalenceRecordWriter {

    void writeLookup(ContentRef subject, Iterable<ContentRef> equivalents, 
            Set<Publisher> publishers);
    
}
