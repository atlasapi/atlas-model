package org.atlasapi.messaging.event;

import org.atlasapi.messaging.worker.Worker;
import org.joda.time.DateTime;

/**
 * Base interface for events to be dispatched to {@link org.atlasapi.persistence.messaging.worker.Worker}s.
 */
public interface Event {
    
    /**
     * Get a unique id for the changeset represented by this event.
     */
    String getChangeId();
    
    /**
     * When the event happened
     */
    DateTime getDateTime();
    
    /**
     * Get the id of the entity this event refers to.
     */
    String getEntityId();
    
    /**
     * Get the type of the entity this event refers to.
     */
    String getEntityType();
    
    /**
     * Get the source identifier of the entity to which this event refers.
     */
    String getEntitySource();
    
    /**
     * Dispatch this event to the given {@link org.atlasapi.persistence.messaging.worker.Worker}.
     */
    void dispatchTo(Worker worker);
}
