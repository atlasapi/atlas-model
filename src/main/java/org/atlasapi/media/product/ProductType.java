package org.atlasapi.media.product;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public enum ProductType {

    CD,
    DVD,
    BLU_RAY,
    DOWNLOAD,
    STREAM;

    public String toString() {
        return super.toString().toLowerCase();
    };

    // TODO: move this to common?
    public static <T> Function<T, Optional<T>> optionalFunction() {
        return new Function<T, Optional<T>>() {
            @Override
            public Optional<T> apply(T input) {
                return Optional.fromNullable(input);
            }

        };
    }

    private static final Map<String, Optional<ProductType>> fromString = ImmutableMap.copyOf(Maps.transformValues(
            Maps.uniqueIndex(ImmutableSet.copyOf(ProductType.values()), Functions.toStringFunction()), ProductType.<ProductType>optionalFunction()));

    public static Optional<ProductType> fromString(@Nullable String typeString) {
        Optional<ProductType> type = fromString.get(typeString);
        return type == null ? Optional.<ProductType> absent() : type;
    }
}
