package org.atlasapi.media.entity.simple;

import java.util.Objects;

import javax.annotation.Nullable;

public class Review {

    private String review;
    private PublisherDetails publisherDetails;
    private String language;
    private String type;
    private Author author;

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    @Nullable
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(@Nullable Author author) {
        this.author = author;
    }

    public String getReview() {
        return review;
    }
    
    public void setReview(String review) {
        this.review = review;
    }
    
    public PublisherDetails getPublisherDetails() {
        return publisherDetails;
    }
    
    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisherDetails = publisherDetails;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Review)) {
            return false;
        }
        Review review1 = (Review) o;
        return Objects.equals(review, review1.review) &&
                Objects.equals(publisherDetails, review1.publisherDetails) &&
                Objects.equals(language, review1.language) &&
                Objects.equals(type, review1.type) &&
                Objects.equals(author, review1.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(review, publisherDetails, language, type, author);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("review", review)
                .add("publisherDetails", publisherDetails)
                .add("language", language)
                .add("type", type)
                .add("author", author)
                .toString();
    }
}
