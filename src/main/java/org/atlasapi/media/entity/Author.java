package org.atlasapi.media.entity;

import java.util.Objects;

public class Author {

    private String authorName;
    private String authorInitials;

    public Author() {

    }

    private Author(Author.Builder builder) {
        this.authorName = builder.authorName;
        this.authorInitials = builder.authorInitials;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorInitials() {
        return authorInitials;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Author)) {
            return false;
        }
        Author author = (Author) o;
        return Objects.equals(authorName, author.authorName) &&
                Objects.equals(authorInitials, author.authorInitials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorName, authorInitials);
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("authorName", authorName)
                .add("authorInitials", authorInitials)
                .toString();
    }

    public Builder copy() {
        return new Builder()
                .withAuthorName(authorName)
                .withAuthorInitials(authorInitials);
    }

    public static class Builder {

        private String authorName;
        private String authorInitials;

        private Builder() {

        }

        public Builder withAuthorName(String authorName) {
            this.authorName = authorName;
            return this;
        }

        public Builder withAuthorInitials(String authorInitials) {
            this.authorInitials = authorInitials;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
}
