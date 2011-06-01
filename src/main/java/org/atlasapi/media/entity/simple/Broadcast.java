package org.atlasapi.media.entity.simple;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="broadcast", namespace=PLAY_SIMPLE_XML.NS)
public class Broadcast extends Version implements Comparable<Broadcast> {
	
    private Date transmissionTime;

    private Date transmissionEndTime;

    private Integer broadcastDuration;

    private String broadcastOn;

    private LocalDate scheduleDate;
    
    private String id;
    
    private Boolean repeat;
    
    private Boolean subtitled;
    
    private Boolean signed;
    
    private Boolean audioDescribed;
    
    private Boolean highDefinition;
    
    private Boolean widescreen;
    
    private Boolean surround;
    
    private Boolean live;

    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime) {
        this(broadcastOn, transmissionTime, transmissionEndTime, null);
    }

    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime, String id) {
		this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime.toDate();
		this.transmissionEndTime = transmissionEndTime.toDate();
		this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
		this.id = id;
	}
    
    public Broadcast() {
    	
    }

	public void setTransmissionTime(Date transmissionTime) {
		this.transmissionTime = transmissionTime;
	}

	public void setTransmissionEndTime(Date transmissionEndTime) {
		this.transmissionEndTime = transmissionEndTime;
	}

	public void setBroadcastDuration(Integer broadcastDuration) {
		this.broadcastDuration = broadcastDuration;
	}

	public void setBroadcastOn(String broadcastOn) {
		this.broadcastOn = broadcastOn;
	}

	public void setScheduleDate(LocalDate scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	
	public void setId(String id) {
	    this.id = id;
	}

	public Date getTransmissionTime() {
		return transmissionTime;
	}

	public Date getTransmissionEndTime() {
		return transmissionEndTime;
	}

	public Integer getBroadcastDuration() {
		return broadcastDuration;
	}

	public String getBroadcastOn() {
		return broadcastOn;
	}

	public LocalDate getScheduleDate() {
		return scheduleDate;
	}
	
	public String getId() {
	    return id;
	}
	
	public Boolean isRepeat() {
	    return repeat;
	}
	
	public Broadcast withRepeat(Boolean repeat) {
	    this.repeat = repeat;
	    return this;
	}
	
	public Broadcast withSubtitled(Boolean subtitled) {
        this.subtitled = subtitled;
        return this;
    }

    public Boolean isSubtitled() {
        return subtitled;
    }

    public Broadcast withSigned(Boolean signed) {
        this.signed = signed;
        return this;
    }

    public Boolean isSigned() {
        return signed;
    }

    public Broadcast withAudioDescribed(Boolean audioDescribed) {
        this.audioDescribed = audioDescribed;
        return this;
    }

    public Boolean isAudioDescribed() {
        return audioDescribed;
    }

    public Broadcast withHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
        return this;
    }

    public Boolean isHighDefinition() {
        return highDefinition;
    }

    public Broadcast withWidescreen(Boolean widescreen) {
        this.widescreen = widescreen;
        return this;
    }

    public Boolean isWidescreen() {
        return widescreen;
    }
    
    public Broadcast withSurround(Boolean surround) {
        this.surround = surround;
        return this;
    }

    public Boolean isSurround() {
        return surround;
    }

    public Broadcast withLive(Boolean live) {
        this.live = live;
        return this;
    }

    public Boolean isLive() {
        return live;
    }
	
	@Override
	public String toString() {
	    return Objects.toStringHelper(this).addValue(id).addValue(broadcastOn).addValue(transmissionTime).addValue(transmissionEndTime).toString();
	}
	
	public Broadcast copy() {
        Broadcast copy = new Broadcast();
        
        copyTo(copy);
        
        if (getTransmissionTime() != null) {
            copy.setTransmissionTime((Date) getTransmissionTime().clone());
        }
        if (getTransmissionEndTime() != null) {
            copy.setTransmissionEndTime((Date) getTransmissionEndTime().clone());
        }
        copy.setBroadcastDuration(getBroadcastDuration());
        copy.setBroadcastOn(getBroadcastOn());
        copy.setScheduleDate(getScheduleDate());
        copy.setId(getId());
        copy.withRepeat(isRepeat());
        copy.withSubtitled(isSubtitled());
        copy.withSigned(isSigned());
        copy.withAudioDescribed(isAudioDescribed());
        copy.withHighDefinition(isHighDefinition());
        copy.withWidescreen(isWidescreen());
        copy.withSurround(isSurround());
        copy.withLive(isLive());
        
        return copy;
    }

	@Override
	public int compareTo(Broadcast other) {
		int startTimeComparison = transmissionTime.compareTo(other.transmissionTime);
		if (startTimeComparison != 0) {
			return startTimeComparison;
		}
		int durationComparison = broadcastDuration.compareTo(other.broadcastDuration);
		if (durationComparison != 0) {
			return durationComparison;
		}
		return broadcastOn.compareTo(other.broadcastOn);
	}
	
	public static final Predicate<Broadcast> IS_CURRENT_OR_UPCOMING = new Predicate<Broadcast>() {
        @Override
        public boolean apply(Broadcast input) {
            return new DateTime(input.getTransmissionEndTime(), DateTimeZone.UTC).isAfterNow();
        }
    };
    
    public static final Function<Broadcast, Broadcast> TO_COPY = new Function<Broadcast, Broadcast>() {
        @Override
        public Broadcast apply(Broadcast input) {
            return input.copy();
        }
    };
}
