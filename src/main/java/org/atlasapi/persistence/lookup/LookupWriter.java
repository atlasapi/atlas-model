package org.atlasapi.persistence.lookup;

import java.util.Set;

import org.atlasapi.media.content.Content;
import org.atlasapi.media.content.Publisher;

public interface LookupWriter {

    <T extends Content> void writeLookup(T subject, Iterable<T> equivalents, Set<Publisher> publishers);

}
