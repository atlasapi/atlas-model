package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class Rating {

    private final float value;
    private final String type;
    private final Publisher publisher;
    private final long numberOfVotes;
    
    public Rating(String type, float value, Publisher publisher, long numberOfVotes) {
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

    public long getNumberOfVotes() {
        return numberOfVotes;
    }
}
