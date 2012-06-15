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

package org.atlasapi.media.content;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Ordering;
import com.metabroadcast.common.base.Maybe;

/**
 * A time and channel at which a Version is/was receivable.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Broadcast extends Identified {

    private final DateTime transmissionTime;

    private final DateTime transmissionEndTime;

    private final Integer broadcastDuration;

    private final String broadcastOn;

    private LocalDate scheduleDate;
    
    private Boolean activelyPublished;
    
    private String sourceId;  
    
    private Boolean repeat;
    
    private Boolean subtitled;
    
    private Boolean signed;
    
    private Boolean audioDescribed;
    
    private Boolean highDefinition;
    
    private Boolean widescreen;
    
    private Boolean surround;
    
    private Boolean live;
    
    private Boolean newSeries;
    
    private Boolean premiere;
    
    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime, Boolean activelyPublished) {
		this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime;
		this.transmissionEndTime = transmissionEndTime;
		this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
		this.activelyPublished = activelyPublished;
	}
    
    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime) {
        this(broadcastOn, transmissionTime, transmissionEndTime, true);
    }
    
    public Broadcast(String broadcastOn,  DateTime transmissionTime, Duration duration) {
        this(broadcastOn, transmissionTime, duration, true);
    }
    
    public Broadcast(String broadcastOn,  DateTime transmissionTime, Duration duration, Boolean activelyPublished) {
    	this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime;
		this.transmissionEndTime = transmissionTime.plus(duration);
		this.broadcastDuration = (int) duration.getStandardSeconds();
		this.activelyPublished = activelyPublished;
	}
    
    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, relation = false)
    public DateTime getTransmissionTime() {
        return this.transmissionTime;
    }

    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, relation = false)
    public DateTime getTransmissionEndTime() {
		return transmissionEndTime;
	}
    
    public Maybe<Interval> transmissionInterval() {
        if (transmissionTime != null && transmissionEndTime != null) {
            return Maybe.fromPossibleNullValue(new Interval(transmissionTime, transmissionEndTime));
        }
        return Maybe.nothing();
    }

    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, relation = false)
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
    
    public String getSourceId() {
        return sourceId;
    }

    public void setScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
    
    public Broadcast withId(String id) {
        this.sourceId = id;
        return this;
    }
    
    public Boolean isActivelyPublished() {
        return activelyPublished;
    }
    
    public void setIsActivelyPublished(Boolean activelyPublished) {
        this.activelyPublished = activelyPublished;
    }
    
    public Boolean getRepeat() {
        return repeat;
    }
    
    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }
    
    public void setSubtitled(Boolean subtitled) {
        this.subtitled = subtitled;
    }

    public Boolean getSubtitled() {
        return subtitled;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setAudioDescribed(Boolean audioDescribed) {
        this.audioDescribed = audioDescribed;
    }

    public Boolean getAudioDescribed() {
        return audioDescribed;
    }

    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }

    public Boolean getHighDefinition() {
        return highDefinition;
    }

    public void setWidescreen(Boolean widescreen) {
        this.widescreen = widescreen;
    }

    public Boolean getWidescreen() {
        return widescreen;
    }
    
    public void setSurround(Boolean surround) {
        this.surround = surround;
    }

    public Boolean getSurround() {
        return surround;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }

    public Boolean getLive() {
        return live;
    }
    
    public void setPremiere(Boolean premiere) {
        this.premiere = premiere;
    }

    public Boolean getPremiere() {
        return premiere;
    }
    
    public void setNewSeries(Boolean newSeries) {
        this.newSeries = newSeries;
    }
    
    public Boolean getNewSeries() {
        return newSeries;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Broadcast)) {
            return false;
        }
        Broadcast broadcast = (Broadcast) object;
        if (sourceId != null && broadcast.sourceId != null) {
        	return sourceId.equals(broadcast.sourceId);
        }
        return broadcastOn.equals(broadcast.broadcastOn) && transmissionTime.equals(broadcast.getTransmissionTime()) && transmissionEndTime.equals(broadcast.getTransmissionEndTime());
    }
    
    @Override
    public int hashCode() {
        // Currently publishers either have ids for all broadcasts or all broadcasts don't have ids 
        // (there are no mixes of broadcasts with and without ids) so this hashCode is safe
    	if (sourceId != null) {
    	    return sourceId.hashCode();
    	}
    	if (transmissionTime != null) {
    	    return transmissionTime.hashCode();
        }
        return 43;
    }
    
    public Broadcast copy() {
        Broadcast copy = new Broadcast(broadcastOn, transmissionTime, transmissionEndTime);
        Identified.copyTo(this, copy);
        copy.activelyPublished = activelyPublished;
        copy.sourceId = sourceId;
        copy.scheduleDate = scheduleDate;
        copy.repeat = repeat;
        copy.subtitled = subtitled;
        copy.signed = signed;
        copy.audioDescribed = audioDescribed;
        copy.highDefinition = highDefinition;
        copy.widescreen = widescreen;
        copy.newSeries = newSeries;
        copy.premiere = premiere;
        return copy;
    }
    
    public final static Function<Broadcast, Broadcast> COPY = new Function<Broadcast, Broadcast>() {
        @Override
        public Broadcast apply(Broadcast input) {
            return input.copy();
        }
    };

    public static final Predicate<Broadcast> IS_REPEAT = new Predicate<Broadcast>() {
        @Override
        public boolean apply(Broadcast input) {
            return input.repeat != null && input.repeat;
        }
    };
    
    public static final Function<Broadcast, DateTime> TO_TRANSMISSION_TIME = new Function<Broadcast, DateTime>() {
        @Override
        public DateTime apply(Broadcast input) {
            return input.getTransmissionTime();
        }
    };
}
