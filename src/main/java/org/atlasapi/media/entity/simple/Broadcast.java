package org.atlasapi.media.entity.simple;

import java.util.Date;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
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
import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="broadcast", namespace=PLAY_SIMPLE_XML.NS)
public class Broadcast extends Version implements Comparable<Broadcast> {
	
    private static final String TEST_CHANNEL_FOR_BLACKOUT_FLAG = "http://ref.atlasapi.org/channels/nickjr";
    
    private Date transmissionTime;

    private Date transmissionEndTime;
    
    private DateTime actualTransmissionTime;
    
    private DateTime actualTransmissionEndTime;

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
    
    private Boolean premier;
    
    private Boolean newSeries;
    
    private Boolean newEpisode;
    
    private Channel channel;
    
    private BlackoutRestriction blackoutRestriction;
    
    private Set<String> aliases = Sets.newHashSet();

    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime) {
        this(broadcastOn, transmissionTime, transmissionEndTime, null);
    }

    public Broadcast(String broadcastOn, DateTime transmissionTime, DateTime transmissionEndTime, String id) {
		this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime.toDate();
		this.transmissionEndTime = transmissionEndTime.toDate();
		this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
		this.id = id;
		
		boolean blackoutRestrction = TEST_CHANNEL_FOR_BLACKOUT_FLAG.equals(broadcastOn)
		                                && transmissionTime != null
		                                && transmissionTime.getHourOfDay() % 2 == 0;
		                                
		this.blackoutRestriction = new BlackoutRestriction(blackoutRestrction);
	}
    
    public Broadcast() {
    	
    }

	public void setTransmissionTime(Date transmissionTime) {
		this.transmissionTime = transmissionTime;
	}

	public void setTransmissionEndTime(Date transmissionEndTime) {
		this.transmissionEndTime = transmissionEndTime;
	}
	
	public void setActualTransmissionTime(DateTime actualTransmissionTime) {
	    this.actualTransmissionTime = actualTransmissionTime;
	}
	
	public void setActualTransmissionEndTime(DateTime actualTransmissionEndTime) {
	    this.actualTransmissionEndTime = actualTransmissionEndTime;
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
    
	public DateTime getActualTransmissionTime() {
        return actualTransmissionTime;
    }
    
    public DateTime getActualTransmissionEndTime() {
        return actualTransmissionEndTime;
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
	
	public Boolean getRepeat() {
        return repeat;
    }

    public void setRepeat(Boolean repeat) {
        this.repeat = repeat;
    }

    public Boolean getSubtitled() {
        return subtitled;
    }

    public void setSubtitled(Boolean subtitled) {
        this.subtitled = subtitled;
    }

    public Boolean getSigned() {
        return signed;
    }

    public void setSigned(Boolean signed) {
        this.signed = signed;
    }

    public Boolean getAudioDescribed() {
        return audioDescribed;
    }

    public void setAudioDescribed(Boolean audioDescribed) {
        this.audioDescribed = audioDescribed;
    }

    public Boolean getHighDefinition() {
        return highDefinition;
    }

    public void setHighDefinition(Boolean highDefinition) {
        this.highDefinition = highDefinition;
    }

    public Boolean getWidescreen() {
        return widescreen;
    }

    public void setWidescreen(Boolean widescreen) {
        this.widescreen = widescreen;
    }
    
    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Boolean getSurround() {
        return surround;
    }

    public void setSurround(Boolean surround) {
        this.surround = surround;
    }

    public Boolean getLive() {
        return live;
    }

    public void setLive(Boolean live) {
        this.live = live;
    }
    
    public Boolean getPremier() {
        return premier;
    }
    
    public void setPremiere(Boolean premier) {
        this.premier = premier;
    }
    
    public Boolean getNewSeries() {
        return newSeries;
    }
    
    public Channel getChannel() {
        return channel;
    }
    
    public void setNewSeries(Boolean newSeries) {
        this.newSeries = newSeries;
    }
    
    public Boolean getNewEpisode() {
        return newEpisode;
    }
    
    public void setNewEpisode(Boolean newEpisode) {
        this.newEpisode = newEpisode;
    }
    
    public void setAliases(Set<String> aliases) {
        this.aliases = aliases;
    }
    
    @XmlElementWrapper(name="aliases")
    @XmlElement(name="alias")
    public Set<String> getAliases() {
        return aliases;
    }

    public BlackoutRestriction getBlackoutRestriction() {
        return blackoutRestriction;
    }

    public void setBlackoutRestriction(BlackoutRestriction blackoutResstriction) {
        this.blackoutRestriction = blackoutResstriction;
    }

    @Override
	public String toString() {
	    return Objects.toStringHelper(this).addValue(id).addValue(broadcastOn).addValue(transmissionTime).addValue(transmissionEndTime).toString();
	}
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Broadcast) {
            return this.compareTo((Broadcast) obj) == 0;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(transmissionTime, broadcastDuration);
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
        copy.setActualTransmissionTime(getActualTransmissionTime());
        copy.setActualTransmissionEndTime(getActualTransmissionEndTime());
        copy.setBroadcastDuration(getBroadcastDuration());
        copy.setBroadcastOn(getBroadcastOn());
        copy.setScheduleDate(getScheduleDate());
        copy.setId(getId());
        copy.setRepeat(getRepeat());
        copy.setSubtitled(getSubtitled());
        copy.setSigned(getSigned());
        copy.setAudioDescribed(getAudioDescribed());
        copy.setHighDefinition(getHighDefinition());
        copy.setWidescreen(getWidescreen());
        copy.setSurround(getSurround());
        copy.setLive(getLive());
        copy.setAliases(getAliases());
        copy.setNewSeries(getNewSeries());
        copy.setNewEpisode(getNewEpisode());
        copy.setBlackoutRestriction(getBlackoutRestriction());
        
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
