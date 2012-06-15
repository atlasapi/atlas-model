package org.atlasapi.media.product;

import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import org.atlasapi.media.content.Described;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;

public class Product extends Described {

    //TODO: move this to common
    public static <T> Function<T, Optional<T>> optionalFunction() {
        return new Function<T, Optional<T>>() {
            @Override
            public Optional<T> apply(T input) {
                return Optional.fromNullable(input);
            }

        };
    }
    
    public enum Type {
        CD, DVD, BLU_RAY, DOWNLOAD, STREAM;
        
        public String toString() {
            return super.toString().toLowerCase();
        };
        
        private static final Map<String, Optional<Type>> fromString = ImmutableMap.copyOf(Maps.transformValues(Maps.uniqueIndex(ImmutableSet.copyOf(Type.values()), Functions.toStringFunction()), Product.<Type>optionalFunction()));

        public static Optional<Type> fromString(@Nullable String typeString) {
            Optional<Type> type = fromString.get(typeString);
            return type == null ? Optional.<Type>absent() : type;
        }
    }

    private String gtin;
    private Type type;
    private Integer year;
    private Set<String> content = ImmutableSet.of();
    private Set<ProductLocation> locations = ImmutableSet.of();

    public String getGtin() {
        return this.gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public Type getType() {
        return this.type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<String> getContent() {
        return this.content;
    }

    public void setContent(Iterable<String> content) {
        this.content = ImmutableSet.copyOf(content);
    }

    public Set<ProductLocation> getLocations() {
        return this.locations;
    }

    public void setLocations(Iterable<ProductLocation> locations) {
        this.locations =  ImmutableSet.copyOf(locations);
    }

    @Override
    public Described copy() {
        Product product = new Product();
        Described.copyTo(this, product);
        product.gtin = this.gtin;
        product.type = this.type;
        product.year = this.year;
        product.content = this.content;
        product.locations = this.locations;
        return product;
    }
}
