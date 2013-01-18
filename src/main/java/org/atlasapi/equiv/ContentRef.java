package org.atlasapi.equiv;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.content.Content;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.ParentRef;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Series;

import com.google.common.base.Function;
import com.google.common.base.Objects;

public class ContentRef {

    public static ContentRef valueOf(Content content) {
        Publisher publisher = content.getPublisher();
        Id id = content.getId();
        Id parentId = null;
        if (content instanceof Item) {
            parentId = uriOrNull(((Item)content).getContainer());
        }
        if (content instanceof Series) {
            parentId = uriOrNull(((Series)content).getParent());
        }
        return new ContentRef(id, publisher, parentId);
    }
    
    public static final Function<Content, ContentRef> FROM_CONTENT = new Function<Content,ContentRef>(){
        @Override
        public ContentRef apply(@Nullable Content input) {
            return ContentRef.valueOf(input);
        };
    };

    protected static Id uriOrNull(ParentRef parent) {
        return parent == null ? null : parent.getId();
    }
    
    private final Id id;
    private final Publisher publisher;
    private final Id parentId;
    
    public ContentRef(long id, Publisher publisher, @Nullable Id parentId) {
        this(Id.valueOf(id), publisher, parentId);
    }

    public ContentRef(Id id, Publisher publisher, @Nullable Id parentId) {
        this.id = checkNotNull(id);
        this.publisher = checkNotNull(publisher);
        this.parentId = parentId;
    }
    
    public Id getId() {
        return this.id;
    }
    
    public Publisher getPublisher() {
        return this.publisher;
    }
    
    public Id getParentId() {
        return this.parentId;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ContentRef) {
            ContentRef other = (ContentRef) that; 
            return parentId.equals(other.parentId);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(getClass())
                .add("id", id)
                .add("publisher", publisher)
                .add("parent", parentId)
                .toString();
    }
}
