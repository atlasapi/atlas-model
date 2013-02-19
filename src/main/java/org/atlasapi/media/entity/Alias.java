package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

public class Alias {

    private final String namespace;
    private final String value;
    
    public Alias(String namespace, String value) {
        checkNotNull(namespace);
        checkNotNull(value);
        this.namespace = namespace;
        this.value = value;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getValue() {
        return value;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(namespace, value);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Alias) {
            Alias other = (Alias)that;
            return namespace.equals(other.namespace) && value.equals(other.value);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return String.format("Alias: Namespace: %s Value: %s", namespace, value);
    }
}
