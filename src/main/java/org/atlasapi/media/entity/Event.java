package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;


public class Event extends Identified {
    
    private String title;
    private Publisher publisher;
    private Topic venue;
    private DateTime startTime;
    private DateTime endTime;
    private List<Person> participants;
    private List<Organisation> organisations;
    private List<Topic> eventGroups;
    private List<ChildRef> content;
    
    public static Builder builder() {
        return new Builder();
    }
    
    public Event(String title, Publisher publisher, @Nullable Topic venue, @Nullable DateTime startTime, 
            @Nullable DateTime endTime, Iterable<Person> participants, Iterable<Organisation> organisations, 
            Iterable<Topic> eventGroups, Iterable<ChildRef> content) {
                this.title = checkNotNull(title);
                this.publisher = checkNotNull(publisher);
                this.venue = venue;
                this.startTime = startTime;
                this.endTime = endTime;
                this.participants = ImmutableList.copyOf(participants);
                this.organisations = ImmutableList.copyOf(organisations);
                this.eventGroups = ImmutableList.copyOf(eventGroups);
                this.content = ImmutableList.copyOf(content);
    }

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

    public DateTime startTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime endTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
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

    public List<ChildRef> content() {
        return content;
    }

    public void setContent(Iterable<ChildRef> content) {
        this.content = ImmutableList.copyOf(content);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(Event.class)
                .add("title", title)
                .add("publisher", publisher)
                .add("venue", venue)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("participants", participants)
                .add("organisations", organisations)
                .add("eventGroups", eventGroups)
                .add("content", content)
                .toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Event) {
            Event target = (Event) obj;
            return getId() != null ? Objects.equal(getId(), target.getId()) 
                                   : Objects.equal(getCanonicalUri(), target.getCanonicalUri());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : getCanonicalUri().hashCode();
    }
    
    public static Function<Event, Event> COPY = new Function<Event, Event>() {
        @Override
        public Event apply(Event input) {
            if (input == null) {
                return null;
            }
            
            Event event = Event.builder()
                    .withTitle(input.title)
                    .withPublisher(input.publisher)
                    .withVenue(input.venue.copy())
                    .withStartTime(input.startTime)
                    .withEndTime(input.endTime)
                    .withParticipants(Iterables.transform(input.participants, Person.COPY))
                    .withOrganisations(Iterables.transform(input.organisations, Organisation.COPY))
                    .withEventGroups(Iterables.transform(input.eventGroups, topicCopy()))
                    .withContent(input.content)
                    .build();
            
            Identified.copyTo(input, event);
            
            return event;
        }
    };
    
    private static Function<Topic, Topic> topicCopy() {
        return new Function<Topic, Topic>() {
            @Override
            public Topic apply(Topic input) {
                return input.copy();
            }
        };
    }
    
    public static class Builder {
        
        private String title;
        private Publisher publisher;
        private Topic venue;
        private DateTime startTime;
        private DateTime endTime;
        private ImmutableList.Builder<Person> participants = ImmutableList.builder();
        private ImmutableList.Builder<Organisation> organisations = ImmutableList.builder();
        private ImmutableList.Builder<Topic> eventGroups = ImmutableList.builder();
        private ImmutableList.Builder<ChildRef> content = ImmutableList.builder();
        
        private Builder() {}
        
        public Event build() {
            return new Event(title, publisher, venue, startTime, endTime, participants.build(), organisations.build(), 
                    eventGroups.build(), content.build());
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }
        
        public Builder withPublisher(Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withVenue(Topic venue) {
            this.venue = venue;
            return this;
        }

        public Builder withStartTime(DateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder withEndTime(DateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder withParticipants(Iterable<Person> participants) {
            this.participants.addAll(participants);
            return this;
        }

        public Builder withOrganisations(Iterable<Organisation> organisations) {
            this.organisations.addAll(organisations);
            return this;
        }

        public Builder withEventGroups(Iterable<Topic> eventGroups) {
            this.eventGroups.addAll(eventGroups);
            return this;
        }

        public Builder withContent(Iterable<ChildRef> content) {
            this.content.addAll(content);
            return this;
        }
    }
}
