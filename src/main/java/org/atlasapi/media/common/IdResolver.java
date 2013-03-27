package org.atlasapi.media.common;

import org.atlasapi.media.util.Resolved;

import com.google.common.util.concurrent.ListenableFuture;

public interface IdResolver<I extends Identifiable> {

    ListenableFuture<Resolved<I>> resolveIds(Iterable<Id> ids);
    
}
