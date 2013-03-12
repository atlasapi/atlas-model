package org.atlasapi.media.common;

import org.atlasapi.media.util.Resolved;

public interface IdResolver<I extends Identifiable> {

    Resolved<I> resolveIds(Iterable<Id> ids);
    
}
