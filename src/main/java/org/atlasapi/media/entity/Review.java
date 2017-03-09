package org.atlasapi.media.entity;

import java.util.Date;
import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Objects;

import static com.google.common.base.Preconditions.checkNotNull;

public class Review {

    private final Locale locale;
    private final String review;
    private final String author;
    private final String authorInitials;
    private final String rating;
    private final Date date;
    private final String reviewTypeKey;
    private final String publisherKey;

    private Review(Builder builder) {
        this.review = checkNotNull(builder.review);
        this.publisherKey = builder.publisherKey;
        this.locale = builder.locale;
        this.author = builder.author;
        this.authorInitials = builder.authorInitials;
        this.reviewTypeKey = builder.reviewTypeKey;
        this.rating = builder.rating;
        this.date = builder.date;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getReview() {
        return review;
    }

    @Nullable
    public String getPublisherKey() {
        return publisherKey;
    }

    @Nullable
    public String getReviewTypeKey() {
        return reviewTypeKey;
    }

    @Nullable
    public Locale getLocale() {
        return locale;
    }

    @Nullable
    public String getAuthor() {
        return author;
    }

    @Nullable
    public String getAuthorInitials() {
        return authorInitials;
    }

    @Nullable
    public String getRating() {
        return rating;
    }

    @Nullable
    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        
        if (that == null || !(that instanceof Review)) {
            return false;
        }
        
        Review thatReview = (Review) that;
        
        return Objects.equal(this.locale, thatReview.locale)
                && Objects.equal(this.review, thatReview.review)
                && Objects.equal(this.publisherKey, thatReview.publisherKey)
                && Objects.equal(this.author, thatReview.author)
                && Objects.equal(this.authorInitials, thatReview.authorInitials)
                && Objects.equal(this.rating, thatReview.rating)
                && Objects.equal(this.date, thatReview.date)
                && Objects.equal(this.reviewTypeKey, thatReview.reviewTypeKey);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale, review, author, authorInitials, rating, reviewTypeKey, date);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Review.class)
                .add("locale", locale)
                .add("review", review)
                .add("rating", rating)
                .add("date", date)
                .add("reviewTypeKey", reviewTypeKey)
                .add("author", author)
                .add("authorInitials", authorInitials)
                .toString();
    }

    public static final class Builder {

        private String author;
        private String authorInitials;
        private String rating;
        private Date date;
        private String reviewTypeKey;
        private Locale locale;
        private String review;
        private String publisherKey;

        private Builder() {
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withAuthorInitials(String authorInitials) {
            this.authorInitials = authorInitials;
            return this;
        }

        public Builder withRating(String rating) {
            this.rating = rating;
            return this;
        }

        public Builder withDate(@Nullable Date date) {
            this.date = date;
            return this;
        }

        public Builder withReviewTypeKey(@Nullable String reviewTypeKey) {
            this.reviewTypeKey = reviewTypeKey;
            return this;
        }

        public Builder withLocale(Locale locale) {
            this.locale = locale;
            return this;
        }

        public Builder withReview(String review) {
            this.review = review;
            return this;
        }

        public Builder withPublisherKey(String publisherKey) {
            this.publisherKey = publisherKey;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}
