package org.atlasapi.media.entity.simple;

import java.util.Collection;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.Maps;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="schedule")
@XmlType(name="schedule", namespace=PLAY_SIMPLE_XML.NS)
public class ScheduleQueryResult {

	private Map<String, ScheduleChannel> schedule = Maps.newHashMap();
	
	public void add(ScheduleChannel channel) {
	    schedule.put(channel.getChannelKey(), channel);
	}

	@XmlElements({ 
		@XmlElement(name = "channels", type = ScheduleChannel.class, namespace=PLAY_SIMPLE_XML.NS)
	})
	public Collection<ScheduleChannel> getChannels() {
		return schedule.values();
	}
	
	public void setChannels(Iterable<ScheduleChannel> channels) {
		schedule = Maps.uniqueIndex(channels, ScheduleChannel.TO_KEY);
	}

	@Override
	public int hashCode() {
		return schedule.hashCode();
	}
	
	public boolean isEmpty() {
	    return schedule.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this instanceof ScheduleQueryResult) {
			ScheduleQueryResult other = (ScheduleQueryResult) obj;
			return schedule.equals(other.schedule);
		}
		return false;
	}
	
	@Override
	public String toString() {
	    return schedule.toString();
	}
}
