package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.atlasapi.persistence.content.ContentCategory.categoryFor;

import java.util.List;

import javax.annotation.Nullable;

import org.atlasapi.persistence.content.ContentCategory;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public class LookupRef {

    public static LookupRef from(Described subject) {
        return new LookupRef(subject.getCanonicalUri(), subject.getId(), subject.getPublisher(), categoryFor(subject));
    }
    
    public static LookupRef from(ChildRef childRef, Publisher publisher) {
        return new LookupRef(childRef.getUri(), childRef.getId(), publisher, ContentCategory.CHILD_ITEM);
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
    
    public static Function<LookupRef,String> TO_URI = new Function<LookupRef, String>() {
        @Override
        public String apply(LookupRef input) {
            return input.uri();
        }
    };
    
    public static Function<LookupRef,Long> TO_ID = new Function<LookupRef, Long>() {
        @Override
        public Long apply(LookupRef input) {
            return input.id();
        }
    };
    
    public static Function<LookupRef,Publisher> TO_SOURCE = new Function<LookupRef, Publisher>() {
        @Override
        public Publisher apply(LookupRef input) {
            return input.publisher();
        }
    };
    
    private final String uri;
    private final Long id;
    private final Publisher publisher;
    private final ContentCategory category;

    public LookupRef(String uri, @Nullable Long id, Publisher publisher, ContentCategory category) {
        this.uri = checkNotNull(uri);
        this.id = id;
        this.publisher = publisher;
        this.category = category;
    }

    public String uri() {
        return uri;
    }
    
    public Long id() {
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
            return uri.equals(other.uri) && publisher.equals(other.publisher);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s/%s (%s)", publisher.key(), id, category);
    }
}
