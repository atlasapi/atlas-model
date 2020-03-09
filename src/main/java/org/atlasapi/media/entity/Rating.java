package org.atlasapi.media.entity;

import java.util.Objects;

import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Rating {

    private final float value;
    private final String type;
    private final Publisher publisher;
    private final Long numberOfVotes;

    public Rating(String type, float value, Publisher publisher) {
        this.type = checkNotNull(type);
        this.value = value;
        this.publisher = checkNotNull(publisher);
        this.numberOfVotes = null;  // field was added later
    }
    
    public Rating(String type, float value, Publisher publisher, Long numberOfVotes) {
        this.type = checkNotNull(type);
        this.value = value;
        this.publisher = checkNotNull(publisher);
        this.numberOfVotes = numberOfVotes;
    }

    public float getValue() {
        return value;
    }

    public String getType() {
        return type;
    }
    
    public Publisher getPublisher() {
        return publisher;
    }

    @Nullable
    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Rating) {
            Rating other = (Rating) that;
            return type.equals(other.type) && value == other.value && Objects.equals(numberOfVotes, other.numberOfVotes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type, publisher, numberOfVotes);
    }
}
