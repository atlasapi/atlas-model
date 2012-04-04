package org.atlasapi.media.content;

import static com.google.common.base.Preconditions.checkNotNull;

public final class KeyPhrase {

    private final String phrase;
    private final Double weighting;
    
    public KeyPhrase(String phrase, Double weighting) {
        this.phrase = checkNotNull(phrase);
        this.weighting = weighting;
    }
    
    public KeyPhrase(String phrase) {
        this(phrase, null);
    }

    public String phrase() {
        return this.phrase;
    }

    public Double weighting() {
        return this.weighting;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof KeyPhrase) {
            KeyPhrase other = (KeyPhrase) that;
            return phrase.equals(other.phrase);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return phrase.hashCode();
    }
    
    @Override
    public String toString() {
        return String.format("%s: %s", phrase, weighting != null ? weighting : "unweighted");
    }
}
