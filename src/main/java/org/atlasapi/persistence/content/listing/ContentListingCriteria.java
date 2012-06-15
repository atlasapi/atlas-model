package org.atlasapi.persistence.content.listing;

import static com.google.common.base.Objects.toStringHelper;

import java.util.List;

import org.atlasapi.media.content.Publisher;
import org.atlasapi.media.content.util.ContentCategory;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

/**
 * Specifies the source and category that should be included in a content listing task.
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 *
 */
public class ContentListingCriteria {

    public static class Builder {
        
        private ContentListingProgress progress = ContentListingProgress.START;
        private List<Publisher> publishers = Lists.newArrayList();
        private List<ContentCategory> categories = Lists.newArrayList();
        
        public Builder forPublishers(List<Publisher> publishers) {
            this.publishers.addAll(publishers);
            return this;
        }
        
        public Builder forPublishers(Publisher... publishers) {
            this.publishers.addAll(ImmutableList.copyOf(publishers));
            return this;
        }
        
        public Builder forPublisher(Publisher publisher) {
            this.publishers.add(publisher);
            return this;
        }
        
        public Builder startingAt(ContentListingProgress progress) {
            this.progress = progress;
            return this;
        }
        
        public Builder forContent(List<ContentCategory> categories) {
            this.categories.addAll(categories);
            return this;
        }
        
        public Builder forContent(ContentCategory... categories) {
            this.categories.addAll(ImmutableList.copyOf(categories));
            return this;
        }
        
        public Builder forContent(ContentCategory table) {
            this.categories.add(table);
            return this;
        }
        
        public ContentListingCriteria build() {
            return new ContentListingCriteria(categories, publishers, progress);
        }
        
    }

    public static final Builder defaultCriteria() { 
        return new Builder();
    }
    

    private final List<ContentCategory> categories;
    private final List<Publisher> publishers;
    private final ContentListingProgress progress;

    private ContentListingCriteria(List<ContentCategory> categories, List<Publisher> publishers, ContentListingProgress progress) {
        this.categories = categories;
        this.publishers = publishers;
        this.progress = progress;
    }
    
    public ContentListingProgress getProgress() {
        return this.progress;
    }
    
    public List<Publisher> getPublishers() {
        return this.publishers;
    }

    public List<ContentCategory> getCategories() {
        return categories;
    }
    
    @Override
    public String toString() {
        return toStringHelper(this).add("tables", categories).add("publishers", publishers).addValue(progress).toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ContentListingCriteria) {
            ContentListingCriteria other = (ContentListingCriteria) that;
            return this.progress.equals(other.progress) && this.publishers.equals(other.publishers) && this.progress.equals(other.progress);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(progress, publishers, progress);
    }
}
