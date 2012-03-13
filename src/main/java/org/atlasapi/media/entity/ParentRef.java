package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;

public class ParentRef {

    public static ParentRef parentRefFrom(Container container) {
        return new ParentRef(container.getCanonicalUri());
    }
    public static Function<Container, ParentRef> T0_PARENT_REF = new Function<Container, ParentRef>() {

        @Override
        public ParentRef apply(Container input) {
            return parentRefFrom(input);
        }
    };
    
    private String uri;

    public ParentRef(String parentCanonicalUri) {
        this.uri = checkNotNull(parentCanonicalUri);
    }

    protected ParentRef() {
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
        return uri;
    }
}
