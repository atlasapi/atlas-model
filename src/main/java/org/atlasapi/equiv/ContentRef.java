package org.atlasapi.equiv;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nullable;

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
        String uri = content.getCanonicalUri();
        String parentUri = null;
        if (content instanceof Item) {
            parentUri = uriOrNull(((Item)content).getContainer());
        }
        if (content instanceof Series) {
            parentUri = uriOrNull(((Series)content).getParent());
        }
        return new ContentRef(uri, publisher, parentUri);
    }
    
    public static final Function<Content, ContentRef> FROM_CONTENT = new Function<Content,ContentRef>(){
        @Override
        public ContentRef apply(@Nullable Content input) {
            return ContentRef.valueOf(input);
        };
    };

    protected static String uriOrNull(ParentRef parent) {
        return parent == null ? null : parent.getUri();
    }
    
    private final String canonicalUri;
    private final Publisher publisher;
    private final String parentUri;
    
    public ContentRef(String canonicalUri, Publisher publisher, @Nullable String parentUri) {
        this.canonicalUri = checkNotNull(canonicalUri);
        this.publisher = checkNotNull(publisher);
        this.parentUri = parentUri;
    }
    
    public String getCanonicalUri() {
        return this.canonicalUri;
    }
    
    public Publisher getPublisher() {
        return this.publisher;
    }
    
    public String getParentUri() {
        return this.parentUri;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ContentRef) {
            ContentRef other = (ContentRef) that; 
            return canonicalUri.equals(other.canonicalUri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return canonicalUri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(getClass())
                .add("uri", canonicalUri)
                .add("publisher", publisher)
                .add("parent", parentUri)
                .toString();
    }
}
