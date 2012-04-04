package org.atlasapi.media.common;

import java.util.Map;

import com.google.common.base.Optional;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

public final class OptionalMap<K, V> extends ForwardingMap<K, Optional<V>> {

    public static final <K,V> OptionalMap<K,V> fromMap(Map<K,V> map) {
        return new OptionalMap<K,V>(map);
    }
    
    private Map<K, Optional<V>> backingMap;
    
    private OptionalMap(Map<K,V> map) {
        Builder<K,Optional<V>> backingMapBuilder = ImmutableMap.builder();
        for (Entry<K, V> entry : map.entrySet()) {
            backingMapBuilder.put(entry.getKey(), Optional.fromNullable(entry.getValue()));
        }
        this.backingMap = backingMapBuilder.build();
    }
    
    @Override
    protected Map<K, Optional<V>> delegate() {
        return backingMap;
    }
    
    @Override
    public Optional<V> get(Object key) {
        Optional<V> optional = super.get(key);
        return optional != null ? optional : Optional.<V>absent();
    }
}
