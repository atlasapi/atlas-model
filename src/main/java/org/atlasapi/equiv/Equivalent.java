package org.atlasapi.equiv;

import java.util.Set;

import org.atlasapi.media.common.Identifiable;
import org.atlasapi.media.common.Sourced;

public interface Equivalent<E extends Equivalent<E>> extends Identifiable, Sourced {

    Set<EquivalenceRef> getEquivalentTo();
    
    E copyWithEquivalentTo(Iterable<EquivalenceRef> equivalents);
    
}
