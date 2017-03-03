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
    private final ReviewType reviewType;

    private Review(Builder builder) {
        this.review = checkNotNull(builder.review);
        this.locale = builder.locale;
        this.author = builder.author;
        this.authorInitials = builder.authorInitials;
        this.reviewType = builder.type;
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
    public ReviewType getReviewType() {
        return reviewType;
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
                && Objects.equal(this.author, thatReview.author)
                && Objects.equal(this.authorInitials, thatReview.authorInitials)
                && Objects.equal(this.rating, thatReview.rating)
                && Objects.equal(this.date, thatReview.date)
                && Objects.equal(this.reviewType, thatReview.reviewType);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale, review, author, authorInitials, rating, reviewType, date);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Review.class)
                .add("locale", locale)
                .add("review", review)
                .add("rating", rating)
                .add("date", date)
                .add("reviewType", reviewType)
                .add("author", author)
                .add("authorInitials", authorInitials)
                .toString();
    }

    public static final class Builder {

        private String author;
        private String authorInitials;
        private String rating;
        private Date date;
        private ReviewType type;
        private Locale locale;
        private String review;

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

        public Builder withDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder withReviewType(@Nullable ReviewType type) {
            this.type = type;
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

        public Review build() {
            return new Review(this);
        }
    }
}
