package org.atlasapi.media.entity.simple;

public class KeyPhrase {

    private String phrase;
    @Deprecated
    private PublisherDetails publisher;
    private Double weighting;
    
    public KeyPhrase() { }

    public KeyPhrase(String phrase, PublisherDetails publisherDetails, Double weighting) {
        this.phrase = phrase;
        this.publisher = publisherDetails;
        this.weighting = weighting;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public PublisherDetails getPublisher() {
        return this.publisher;
    }

    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }

    public Double getWeighting() {
        return this.weighting;
    }

    public void setWeighting(Double weighting) {
        this.weighting = weighting;
    }
    
}
