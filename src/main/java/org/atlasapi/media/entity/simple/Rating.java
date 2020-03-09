package org.atlasapi.media.entity.simple;

import java.util.Objects;

import javax.annotation.Nullable;

public class Rating {

    private PublisherDetails publisherDetails;
    private Float value;
    private String type;
    private Long numberOfVotes;
    
    public PublisherDetails getPublisherDetails() {
        return publisherDetails;
    }
    
    public void setPublisherDetails(PublisherDetails publisherDetails) {
        this.publisherDetails = publisherDetails;
    }
    
    public Float getValue() {
        return value;
    }
    
    public void setValue(Float value) {
        this.value = value;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    @Nullable
    public Long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Rating) {
            Rating other = (Rating) that;
            return type.equals(other.type) && value.equals(other.value) && Objects.equals(numberOfVotes, other.numberOfVotes);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherDetails, value, type, numberOfVotes);
    }
}
