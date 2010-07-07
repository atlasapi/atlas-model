/* Copyright 2009 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PLAY;
import org.atlasapi.media.vocabulary.PO;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 * A time and channel at which a Version is/was receivable.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Broadcast extends Description {

    private final DateTime transmissionTime;

    private final DateTime transmissionEndTime;

    private final Integer broadcastDuration;

    private final String broadcastOn;

    private LocalDate scheduleDate;

    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime) {
		this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime;
		this.transmissionEndTime = transmissionEndTime;
		this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
	}
    
    public Broadcast(String broadcastOn,  DateTime transmissionTime, Duration duration) {
    	this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime;
		this.transmissionEndTime = transmissionTime.plus(duration);
		this.broadcastDuration = (int) duration.getStandardSeconds();
	}
    
    @RdfProperty(namespace = PLAY.NS, relation = false)
    public DateTime getTransmissionTime() {
        return this.transmissionTime;
    }

    @RdfProperty(namespace = PLAY.NS, relation = false)
    public DateTime getTransmissionEndTime() {
		return transmissionEndTime;
	}

    @RdfProperty(namespace = PLAY.NS, relation = false)
    public Integer getBroadcastDuration() {
        return this.broadcastDuration;
    }

    @RdfProperty(namespace = PO.NS, relation = false)
    public String getBroadcastOn() {
        return broadcastOn;
    }

    @RdfProperty(namespace = PO.NS, relation = false)
    public LocalDate getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
 
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Broadcast)) {
            return false;
        }

        Broadcast broadcast = (Broadcast) object;

        if (null != getCanonicalUri() && null != broadcast.getCanonicalUri() && getCanonicalUri().equals(broadcast.getCanonicalUri())) {
            return true;
        }

        return broadcastOn.equals(broadcast.broadcastOn) && transmissionTime.equals(broadcast.getTransmissionTime()) && transmissionEndTime.equals(broadcast.getTransmissionEndTime());

    }
}
