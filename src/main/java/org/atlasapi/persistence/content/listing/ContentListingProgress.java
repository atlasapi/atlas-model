package org.atlasapi.persistence.content.listing;

import static org.atlasapi.persistence.content.ContentCategory.categoryFor;

import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.persistence.content.ContentCategory;

import com.google.common.base.Objects;

/**
 * Represents the progress of a content listing operation. 
 * 
 * It stores the necessary state such that a task can be stopped and restarted.
 *
 * @see ContentLister
 * @see ContentListingCriteria
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public class ContentListingProgress {

    public static final ContentListingProgress progressFrom(Content content) {
        return new ContentListingProgress(categoryFor(content), content.getPublisher(), content.getCanonicalUri());
    }

    public static final ContentListingProgress START = new ContentListingProgress(null, null, null);

    private final ContentCategory category;
    private final Publisher publisher;
    private final String initialId;

    public ContentListingProgress(ContentCategory table, Publisher publisher, String initialId) {
        this.category = table;
        this.publisher = publisher;
        this.initialId = initialId;
    }

    public ContentCategory getCategory() {
        return category;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getUri() {
        return initialId;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("category", category).add("publisher", publisher).add("id", initialId).toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ContentListingProgress) {
            ContentListingProgress other = (ContentListingProgress) that;
            return Objects.equal(this.category, other.category) && Objects.equal(this.publisher, other.publisher) && Objects.equal(this.initialId, other.initialId);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(category, publisher, initialId);
    }
}
