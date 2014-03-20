package org.atlasapi.media.entity;

import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Objects;


public class Review {

    private final Locale locale;
    private final String review;
    
    public Review(@Nullable Locale locale, String review) {
        this.locale = locale;
        this.review = review;
    }
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getReview() {
        return review;
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
                && Objects.equal(this.review, thatReview.review);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale, review);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Review.class)
                        .add("locale", locale)
                        .add("review", review)
                        .toString();
    }
}
