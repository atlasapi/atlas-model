package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

public interface EquivalenceRecordWriter {

    void writeRecord(EquivalenceRef subject, Iterable<EquivalenceRef> equivalents, 
            Set<Publisher> publishers);
    
}
