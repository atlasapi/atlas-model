package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Container;

import com.google.common.base.Function;

public class ParentRef {
    
    public static ParentRef parentRefFrom(Container container) {
        return new ParentRef(container.getId(), EntityType.from(container));
    }
    
    public static Function<Container, ParentRef> T0_PARENT_REF = new Function<Container, ParentRef>() {
        @Override
        public ParentRef apply(Container input) {
            return parentRefFrom(input);
        }
    };

    private final Id id;
    private final EntityType type;
    
    public ParentRef(long id, EntityType type) {
        this(Id.valueOf(id), type);
    }

    public ParentRef(Id id, EntityType type) {
        this.id = checkNotNull(id);
        this.type = checkNotNull(type);
    }

    public Id getId() {
        return id;
    }
    
    public EntityType getType() {
        return type;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ParentRef) {
            ParentRef other = (ParentRef) that;
            return other.id.equals(id);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return id.toString();
    }
}
