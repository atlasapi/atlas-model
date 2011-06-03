package org.atlasapi.media.entity;

import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Function;
import com.google.common.base.Objects;

public class LookupRef {
    
    public enum LookupType {
        CONTAINER, TOP_LEVEL_ITEM, CHILD_ITEM;
        
        public static LookupType fromContent(Described c) {
            if (c instanceof Item) {
                Item item = (Item) c;
                if (item.getContainer() != null) {
                    return CHILD_ITEM;
                } else {
                    return TOP_LEVEL_ITEM;
                }
            } else if (c instanceof Container<?>) {
                return CONTAINER;
            } else {
                return null;
            }
        }
    }

    public static LookupRef from(Described subject) {
        return new LookupRef(subject.getCanonicalUri(), subject.getPublisher(), LookupType.fromContent(subject));
    }
    
    public static Function<Described,LookupRef> FROM_DESCRIBED = new Function<Described, LookupRef>() {
        @Override
        public LookupRef apply(Described input) {
            return LookupRef.from(input);
        }
    };
    
    public static Function<LookupRef,String> TO_ID = new Function<LookupRef, String>() {
        @Override
        public String apply(LookupRef input) {
            return input.id();
        }
    };
    
    private final String id;
    private final Publisher publisher;
    private final LookupType type;

    public LookupRef(String id, Publisher publisher, LookupType type) {
        this.id = id;
        this.publisher = publisher;
        this.type = type;
    }

    public String id() {
        return id;
    }

    public Publisher publisher() {
        return publisher;
    }

    public LookupType type() {
        return type;
    }
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof LookupRef) {
            LookupRef other = (LookupRef) that;
            return id.equals(other.id) && publisher.equals(other.publisher) && type.equals(other.type);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id, publisher, type);
    }
    
    @Override
    public String toString() {
        return String.format("Equiv:%s(%s,%s)", id, publisher.title(), type);
    }
}
