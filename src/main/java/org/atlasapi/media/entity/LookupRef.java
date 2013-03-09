package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.atlasapi.persistence.content.ContentCategory.categoryFor;

import java.util.List;

import org.atlasapi.media.common.Id;
import org.atlasapi.persistence.content.ContentCategory;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

@Deprecated
public class LookupRef {

    public static LookupRef from(Described subject) {
        return new LookupRef(subject.getId(), subject.getPublisher(), categoryFor(subject));
    }
    
    public static LookupRef from(ChildRef childRef, Publisher publisher) {
        return new LookupRef(childRef.getId(), publisher, ContentCategory.CHILD_ITEM);
    }
    
    public static Function<Described,LookupRef> FROM_DESCRIBED = new Function<Described, LookupRef>() {
        @Override
        public LookupRef apply(Described input) {
            return LookupRef.from(input);
        }
    };
    
    public static List<LookupRef> fromChildRefs(Iterable<ChildRef> childRefs, Publisher publisher) {
        Builder<LookupRef> lookupRefs = ImmutableList.builder();
        
        for (ChildRef childRef : childRefs) {
            lookupRefs.add(from(childRef, publisher));
        }
        
        return lookupRefs.build();
    }
    
    public static Function<LookupRef,Id> TO_ID = new Function<LookupRef, Id>() {
        @Override
        public Id apply(LookupRef input) {
            return input.id();
        }
    };
    
    public static Function<LookupRef,Publisher> TO_SOURCE = new Function<LookupRef, Publisher>() {
        @Override
        public Publisher apply(LookupRef input) {
            return input.publisher();
        }
    };
    
    private final Id id;
    private final Publisher publisher;
    private final ContentCategory category;

    public LookupRef(Id id, Publisher publisher, ContentCategory category) {
        this.id = checkNotNull(id);
        this.publisher = publisher;
        this.category = category;
    }

    public Id id() {
        return id;
    }

    public Publisher publisher() {
        return publisher;
    }

    public ContentCategory category() {
        return category;
    }
    
    @Override
    public boolean equals(Object that) {
        if(this == that) {
            return true;
        }
        if(that instanceof LookupRef) {
            LookupRef other = (LookupRef) that;
            return id.equals(other.id) && publisher.equals(other.publisher);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s/%s (%s)", publisher.key(), id, category);
    }
}
