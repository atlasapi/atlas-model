package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

/**
 */
public abstract class  BroadcastConfiguration {
    
    @JsonCreator
    BroadcastConfiguration(@JsonProperty("broadcastOn") String broadcastOn, @JsonProperty("transmissionTime") DateTime transmissionTime, @JsonProperty("transmissionEndTime")  DateTime transmissionEndTime, @JsonProperty("activelyPublished")  Boolean activelyPublished) {
        
    }
}
