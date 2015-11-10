package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Function;


public class EventRef {

    public static final Function<EventRef, EventRef> COPY = new Function<EventRef, EventRef>() {
        @Override
        public EventRef apply(EventRef input) {
            return new EventRef(input.id(), Event.COPY.apply(input.event()));
        }
    };
    
    private final Long id;
    private Event event;
    private Publisher publisher;

    public EventRef(Long id) {
        this.id = checkNotNull(id);
    }
    
    public EventRef(Long id, Event event) {
        this.id = checkNotNull(id);
        this.event = event;
    }

    public EventRef(Long id, Publisher publisher) {
        this.id = checkNotNull(id);
        this.publisher = publisher;
    }
    
    public Long id() {
        return id;
    }
    
    public Event event() {
        return event;
    }
    
    public void setEvent(Event event) {
        this.event = event;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    
    public static Function<Event, EventRef> toEventRef() {
        return new Function<Event, EventRef>() {
            @Override
            public EventRef apply(Event input) {
                return new EventRef(input.getId(), input);
            }
        };
    }
}
