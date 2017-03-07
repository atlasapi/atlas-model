package org.atlasapi.media.entity.simple;

import org.atlasapi.media.entity.ReviewType;

import java.util.Date;

public class Review {

    private String review;
    private PublisherDetails publisherDetails;
    private String language;
    private String author;
    private String authorInitials;
    private String rating;
    private Date date;
    private ReviewType reviewType;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorInitials() {
        return authorInitials;
    }

    public void setAuthorInitials(String authorInitials) {
        this.authorInitials = authorInitials;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(ReviewType reviewType) {
        this.reviewType = reviewType;
    }
}
