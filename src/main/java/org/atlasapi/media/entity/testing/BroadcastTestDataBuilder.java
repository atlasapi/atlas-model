package org.atlasapi.media.entity.testing;

import java.util.Date;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.entity.simple.Broadcast;
import org.atlasapi.media.entity.simple.Restriction;
import org.joda.time.LocalDate;

public class BroadcastTestDataBuilder {
    private static Integer uniqueId = 1;

    private Integer duration;
    private Integer publishedDuration;
    private Restriction restriction;
    
    private Date transmissionTime;
    private Date transmissionEndTime;
    private Integer broadcastDuration;
    private String broadcastOn;
    private LocalDate scheduleDate;
    private String id;
    
    
    public static BroadcastTestDataBuilder broadcast() {
        return new BroadcastTestDataBuilder();
    }
    
    private BroadcastTestDataBuilder() {
        resetAttributes();
    }
    
    private void resetAttributes() {
        duration = 28;
        publishedDuration = 30;
        restriction = new Restriction();
        restriction.setMessage("restriction message");
        restriction.setMinimumAge(18);
        restriction.setRestricted(true);
        
        transmissionTime = new Date();
        transmissionEndTime = new Date();
        broadcastDuration = 30;
        broadcastOn = "bbcone";
        scheduleDate = new LocalDate();
        id = String.valueOf(uniqueId++);
    }
    
    public Broadcast build() {
        return buildItemAttributes(new Broadcast());
    }
    
    private Broadcast buildItemAttributes(Broadcast location) {
        location.setDuration(duration);
        location.setPublishedDuration(publishedDuration);
        location.setRestriction(restriction);
        
        location.setTransmissionTime(transmissionTime);
        location.setTransmissionEndTime(transmissionEndTime);
        location.setBroadcastDuration(broadcastDuration);
        location.setBroadcastOn(broadcastOn);
        location.setScheduleDate(scheduleDate);
        location.setId(id);
        
        return location;
    }
}
