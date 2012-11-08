package org.atlasapi.media.entity.simple;

import org.joda.time.DateTime;

public class Audit {

    private DateTime lastUpdated;
    private DateTime equivalenceLastUpdated;

    public DateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(DateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public DateTime getEquivalenceLastUdpated() {
        return this.equivalenceLastUpdated;
    }

    public void setEquivalenceLastUdpated(DateTime equivalenceUdpate) {
        this.equivalenceLastUpdated = equivalenceUdpate;
    }

    
}
