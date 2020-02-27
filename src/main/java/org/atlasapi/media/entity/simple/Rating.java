package org.atlasapi.media.entity.simple;

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

    public long getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(long numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Rating) {
            Rating other = (Rating) that;
            return type.equals(other.type) && value.equals(other.value) && numberOfVotes.equals(other.numberOfVotes);
        }
        return false;
    }

}
