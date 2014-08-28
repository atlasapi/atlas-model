package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="events")
@XmlType(name="events", namespace=PLAY_SIMPLE_XML.NS)
public class EventQueryResult {
    
    private List<Event> events = Lists.newArrayList();

    public void add(Event event) {
        events.add(event);
    }

    @XmlElements({ 
        @XmlElement(name = "event", type = Event.class, namespace=PLAY_SIMPLE_XML.NS)
    })
    public List<Event> getEvents() {
        return events;
    }
    
    public void setContents(Iterable<Event> events) {
        this.events = Lists.newArrayList(events);
    }
    
    public boolean isEmpty() {
        return events.isEmpty();
    }

    @Override
    public int hashCode() {
        return events.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this instanceof EventQueryResult) {
            EventQueryResult other = (EventQueryResult) obj;
            return events.equals(other.events);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(events).toString();
    }
}
