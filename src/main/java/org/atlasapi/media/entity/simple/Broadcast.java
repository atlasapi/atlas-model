package org.atlasapi.media.entity.simple;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="broadcast", namespace=PLAY_SIMPLE_XML.NS)
public class Broadcast extends Version implements Comparable<Broadcast> {
	
    private Date transmissionTime;

    private Date transmissionEndTime;

    private Integer broadcastDuration;

    private String broadcastOn;

    private LocalDate scheduleDate;

    public Broadcast(String broadcastOn,  DateTime transmissionTime, DateTime transmissionEndTime) {
		this.broadcastOn = broadcastOn;
		this.transmissionTime = transmissionTime.toDate();
		this.transmissionEndTime = transmissionEndTime.toDate();
		this.broadcastDuration = (int) new Duration(transmissionTime, transmissionEndTime).getStandardSeconds();
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
}
