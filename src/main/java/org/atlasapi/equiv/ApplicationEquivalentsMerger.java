package org.atlasapi.equiv;

import java.util.List;

import org.atlasapi.application.ApplicationSources;

public interface ApplicationEquivalentsMerger<E extends Equivalent<E>> {

    <T extends E> List<T> merge(Iterable<T> equivalents, ApplicationSources sources);
    
}
