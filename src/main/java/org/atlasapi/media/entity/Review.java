package org.atlasapi.media.entity;

import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

import com.google.common.base.Objects;

public class Review {

    private final Locale locale;
    private final String review;
    private String type;
    private Author author;

    public Review(@Nullable Locale locale, String review) {
        this.locale = locale;
        this.review = review;
    }

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
    
    public Locale getLocale() {
        return locale;
    }
    
    public String getReview() {
        return review;
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
        return java.util.Objects.equals(locale, review1.locale) &&
                java.util.Objects.equals(review, review1.review) &&
                java.util.Objects.equals(type, review1.type) &&
                java.util.Objects.equals(author, review1.author);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(locale, review, type, author);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("locale", locale)
                .add("review", review)
                .add("type", type)
                .add("author", author)
                .toString();
    }
}
