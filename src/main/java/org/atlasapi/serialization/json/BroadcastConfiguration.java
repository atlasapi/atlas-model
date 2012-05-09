package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.atlasapi.media.entity.Broadcast;
import org.joda.time.DateTime;

/**
 */
public interface BroadcastConfiguration {
    
    @JsonCreator
    Broadcast make(@JsonProperty("broadcastOn") String broadcastOn, @JsonProperty("transmissionTime") DateTime transmissionTime, @JsonProperty("transmissionEndTime")  DateTime transmissionEndTime, @JsonProperty("activelyPublished")  Boolean activelyPublished);
}
