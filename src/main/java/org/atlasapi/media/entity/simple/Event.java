package org.atlasapi.media.entity.simple;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="Event", namespace=PLAY_SIMPLE_XML.NS)
public class Event extends Aliased {

    private String title;
    private Publisher publisher;
    private Topic venue;
    private Date startTime;
    private Date endTime;
    private List<Person> participants = Lists.newArrayList();
    private List<Organisation> organisations = Lists.newArrayList();
    private List<Topic> eventGroups = Lists.newArrayList();
    private List<ContentIdentifier> content = Lists.newArrayList();
    
    public Event() { }
    
    public String title() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Publisher publisher() {
        return publisher;
    }
    
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    } 
    
    public Topic venue() {
        return venue;
    }
    
    public void setVenue(Topic venue) {
        this.venue = venue;
    }
    
    public Date startTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date endTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Person> participants() {
        return participants;
    }

    public void setParticipants(Iterable<Person> participants) {
        this.participants = ImmutableList.copyOf(participants);
    }

    public List<Organisation> organisations() {
        return organisations;
    }

    public void setOrganisations(Iterable<Organisation> organisations) {
        this.organisations = ImmutableList.copyOf(organisations);
    }

    public List<Topic> eventGroups() {
        return eventGroups;
    }
    
    public void setEventGroups(Iterable<Topic> eventGroups) {
        this.eventGroups = ImmutableList.copyOf(eventGroups);
    }
    
    public void setContent(Iterable<ContentIdentifier> content) {
        this.content = ImmutableList.copyOf(content);
    }
    
    public List<ContentIdentifier> content() {
        return content;
    }
    
    public Event copy() {
        Event event = new Event();
        
        copyTo(event);
        
        event.setTitle(title);
        event.setVenue(venue);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setParticipants(participants);
        event.setOrganisations(organisations);
        event.setEventGroups(eventGroups);
        event.setContent(content);
        
        return event;
    }
    
    public static Function<Event, Event> COPY = new Function<Event, Event>() {
        @Override
        public Event apply(Event input) {
            return input.copy();
        }
    };
}
