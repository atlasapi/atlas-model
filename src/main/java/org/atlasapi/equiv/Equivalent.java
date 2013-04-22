package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.media.common.Identifiable;
import org.atlasapi.media.common.Sourced;

public interface Equivalent<E extends Equivalent<E>> extends Identifiable, Sourced {

    Set<EquivalenceRef> getEquivalentTo();
    
    //I don't like this method.
    E copyWithEquivalentTo(Iterable<EquivalenceRef> equivalents);
    
}
