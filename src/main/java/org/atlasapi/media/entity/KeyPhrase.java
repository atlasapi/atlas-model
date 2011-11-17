package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Objects;

public final class KeyPhrase {

    private String phrase;
    private Publisher publisher;
    private Double weighting;
    
    public KeyPhrase(String phrase, Publisher publisher, Double weighting) {
        this.phrase = checkNotNull(phrase);
        this.publisher = checkNotNull(publisher);
        this.weighting = weighting;
    }
    
    public KeyPhrase(String phrase, Publisher publisher) {
        this(phrase, publisher, null);
    }

    public String getPhrase() {
        return this.phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Double getWeighting() {
        return this.weighting;
    }

    public void setWeighting(Double weighting) {
        this.weighting = weighting;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof KeyPhrase) {
            KeyPhrase other = (KeyPhrase) that;
            return phrase.equals(other.phrase) && publisher.equals(other.publisher) && Objects.equal(weighting, other.weighting);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(phrase, publisher, weighting);
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s): %s", phrase, publisher.toString(), weighting != null ? weighting : "unweighted");
    }
}
