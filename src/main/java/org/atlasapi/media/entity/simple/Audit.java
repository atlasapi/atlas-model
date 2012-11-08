package org.atlasapi.media.entity.simple;

import org.joda.time.DateTime;

public class Audit {

    private DateTime lastUpdated;
    private DateTime equivalenceLastUdpated;

    public DateTime getLastUpdated() {
        return this.lastUpdated;
    }

    public void setLastUpdated(DateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public DateTime getEquivalenceLastUdpated() {
        return this.equivalenceLastUdpated;
    }

    public void setEquivalenceLastUdpated(DateTime equivalenceUdpate) {
        this.equivalenceLastUdpated = equivalenceUdpate;
    }

    
}
