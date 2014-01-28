package org.atlasapi.media.entity.simple;

import static com.google.common.base.Preconditions.checkNotNull;
import com.google.common.base.Objects;


public class Alias {

    private String namespace;
    private String value;
    
    public Alias() {
    
    }
    
    public Alias(String namespace, String value) {
        setNamespace(namespace);
        setValue(value);
    }

    public String getNamespace() {
        return namespace;
    }

    public String getValue() {
        return value;
    }
    
    public void setNamespace(String namespace) {
        this.namespace = checkNotNull(namespace);
    }
    
    public void setValue(String value) {
        this.value = checkNotNull(value);
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
