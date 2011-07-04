package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class ParentRef {
    
    public static ParentRef parentRefFrom(Container<? extends Item> container) {
        return new ParentRef(container.getCanonicalUri());
    }

    private final String uri;

    public ParentRef(String parentCanonicalUri) {
        this.uri = checkNotNull(parentCanonicalUri);
    }

    public String getUri() {
        return uri;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ParentRef) {
            ParentRef other = (ParentRef) that;
            return other.uri.equals(uri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("ParentRef:%s",uri);
    }
}
