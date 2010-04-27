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

package org.uriplay.media.entity;

import org.jherd.rdf.annotations.RdfClass;
import org.jherd.rdf.annotations.RdfProperty;
import org.joda.time.DateTime;
import org.uriplay.media.vocabulary.PLAY;
import org.uriplay.media.vocabulary.PO;

/**
 * A time and channel at which a Version is/was receivable.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Broadcast extends Description {

    private DateTime transmissionTime;

    private Integer broadcastDuration;

    private String broadcastOn;

    private String scheduleDate;

    @RdfProperty(namespace = PLAY.NS, relation = false)
    public DateTime getTransmissionTime() {
        return this.transmissionTime;
    }

    public void setTransmissionTime(DateTime transmissionTime) {
        this.transmissionTime = transmissionTime;
    }

    @RdfProperty(namespace = PLAY.NS, relation = false)
    public Integer getBroadcastDuration() {
        return this.broadcastDuration;
    }

    public void setBroadcastDuration(Integer broadcastDuration) {
        this.broadcastDuration = broadcastDuration;
    }

    @RdfProperty(namespace = PO.NS, relation = false)
    public String getBroadcastOn() {
        return broadcastOn;
    }

    public void setBroadcastOn(String broadcastOn) {
        this.broadcastOn = broadcastOn;
    }

    @RdfProperty(namespace = PO.NS, relation = false)
    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
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

        if (((null == broadcastOn && null == broadcast.getBroadcastOn()) || (null != broadcastOn && null != broadcast.getBroadcastOn() && broadcastOn.equals(broadcast.getBroadcastOn())))
                && ((null == getTransmissionTime() && null == broadcast.getTransmissionTime()) || (null != getTransmissionTime() && null != broadcast.getTransmissionTime() && getTransmissionTime()
                        .equals(broadcast.getTransmissionTime())))) {
            return true;
        }

        return false;
    }
}
