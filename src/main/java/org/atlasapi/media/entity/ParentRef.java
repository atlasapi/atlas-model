package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class ParentRef {
    
    public static ParentRef parentRefFrom(Container<? extends Item> container) {
        return new ParentRef(container.getCanonicalUri());
    }

    private final String parentCanonicalUri;

    public ParentRef(String parentCanonicalUri) {
        this.parentCanonicalUri = checkNotNull(parentCanonicalUri);
    }

    public String getParentUri() {
        return parentCanonicalUri;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ParentRef) {
            ParentRef other = (ParentRef) that;
            return other.parentCanonicalUri.equals(parentCanonicalUri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return parentCanonicalUri.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("ParentRef:%s",parentCanonicalUri);
    }
}
