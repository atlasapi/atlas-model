package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;


public class SimilarContentRef {

    private final EntityType entityType;
    private final String uri;
    private final long id;
    private final int score;
    private final Set<Publisher> publishersWithAvailableContent;
    private final Set<Publisher> publishersWithUpcomingContent;
    
    private SimilarContentRef(EntityType entityType, String uri, long id, int score, 
            ImmutableSet<Publisher> publishersWithAvailableContent, ImmutableSet<Publisher> publishersWithUpcomingContent) {
        this.entityType = checkNotNull(entityType);
        this.id = id;
        this.uri = checkNotNull(uri);
        this.score = score;
        this.publishersWithAvailableContent = publishersWithAvailableContent;
        this.publishersWithUpcomingContent = publishersWithUpcomingContent;
    }
    
    public static Builder builder() {
        return new Builder();
    }
    
    public EntityType getEntityType() {
        return entityType;
    }
    
    public long getId() {
        return id;
    }
    
    public String getUri() {
        return uri;
    }
    
    public int getScore() {
        return score;
    }
    
    public Set<Publisher> getPublishersWithAvailableContent() {
        return publishersWithAvailableContent;
    }
    
    public Set<Publisher> getPublishersWithUpcomingContent() {
        return publishersWithUpcomingContent;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof SimilarContentRef) {
            SimilarContentRef other = (SimilarContentRef) that;
            return uri.equals(other.uri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(getClass())
                .add("uri", uri)
                .add("score", score)
                .toString();
    }
    
    public static class Builder {
        
        private EntityType entityType;
        private ImmutableSet<Publisher> publishersWithAvailableContent;
        private ImmutableSet<Publisher> publishersWithUpcomingContent;
        private Integer score;
        private Long id;
        private String uri;
        
        protected Builder() {
            
        }

        public static Builder from(SimilarContentRef from) {
            return new Builder()
                         .withEntityType(from.entityType)
                         .withScore(from.score)
                         .withId(from.id)
                         .withUri(from.uri)
                         .withPublishersWithAvailableContent(from.publishersWithAvailableContent)
                         .withPublishersWithUpcomingContent(from.publishersWithUpcomingContent);
        }
        
        public Builder withEntityType(EntityType entityType) {
            this.entityType = entityType;
            return this;
        }
        
        public Builder withPublishersWithAvailableContent(Iterable<Publisher> publishers) {
            this.publishersWithAvailableContent = ImmutableSet.copyOf(publishers);
            return this;
        }
        
        public Builder withPublishersWithUpcomingContent(Iterable<Publisher> publishers) {
            this.publishersWithUpcomingContent = ImmutableSet.copyOf(publishers);
            return this;
        }
        
        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        
        public Builder withScore(Integer score) {
            this.score = score;
            return this;
        }
        
        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }
        
        public SimilarContentRef build() {
            return new SimilarContentRef(entityType, uri, id, score, publishersWithAvailableContent, 
                    publishersWithUpcomingContent);
        }
        
    }
}
