package org.atlasapi.media.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.annotation.Nullable;

import org.atlasapi.media.content.Identified;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class Resolved<K, V extends Identified> extends ForwardingMap<K, Optional<V>> {

    public static class Builder<K, V extends Identified> {

        private final ImmutableMap.Builder<K, Optional<V>> delegateBuilder = ImmutableMap.builder();

        public Builder<K, V> put(K key, @Nullable V value) {
            delegateBuilder.put(key, Optional.fromNullable(value));
            return this;
        }

        public Builder<K, V> put(K key, Optional<V> value) {
            delegateBuilder.put(key, checkNotNull(value, "Null value"));
            return this;
        }

        public Builder<K, V> put(Iterable<V> values, Function<? super V, K> keyFunction) {
            delegateBuilder.putAll(Maps.transformValues(Maps.uniqueIndex(values, keyFunction), new Function<V, Optional<V>>() {
                @Override
                public Optional<V> apply(V input) {
                    return Optional.fromNullable(input);
                }
            }));
            return this;
        }

        public Resolved<K, V> build() {
            return new Resolved<K, V>(delegateBuilder.build());
        }

    }

    private final ImmutableMap<K, Optional<V>> delegate;

    public Resolved(ImmutableMap<K, Optional<V>> delegate) {
        this.delegate = delegate;
    }

    @Override
    protected Map<K, Optional<V>> delegate() {
        return delegate;
    }

    @Override
    public Optional<V> get(@Nullable Object key) {
        Optional<V> resolved = super.get(key);
        return resolved == null ? Optional.<V> absent() : resolved;
    }
}
