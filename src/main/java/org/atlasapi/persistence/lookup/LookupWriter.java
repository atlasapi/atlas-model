package org.atlasapi.persistence.lookup;

import java.util.Set;

import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Publisher;

public interface LookupWriter {

    <T extends Content> void writeLookup(T subject, Iterable<T> equivalents, Set<Publisher> publishers);

}
