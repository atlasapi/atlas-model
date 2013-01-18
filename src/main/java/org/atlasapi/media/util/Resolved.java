package org.atlasapi.media.util;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.common.Identifiable;

import com.google.common.collect.FluentIterable;
import com.metabroadcast.common.collect.ImmutableOptionalMap;
import com.metabroadcast.common.collect.OptionalMap;

public final class Resolved<R extends Identifiable> {

    public static final <R extends Identifiable> Resolved<R> valueOf(Iterable<R> resources) {
        return new Resolved<R>(resources);
    }
    
    private final FluentIterable<R> resources;
    private OptionalMap<Id, R> toMap;

    public Resolved(Iterable<R> resources) {
        this.resources = FluentIterable.from(resources);
    }

    public FluentIterable<R> getResources() {
        return resources;
    }

    public OptionalMap<Id, R> toMap() {
        if (toMap == null) {
            toMap = ImmutableOptionalMap.fromMap(
                resources.uniqueIndex(Identifiables.toId()));
        }
        return toMap;
    }
    
    @Override
    public String toString() {
        return resources.toString();
    }
    
}
