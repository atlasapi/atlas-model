package org.atlasapi.persistence.messaging.worker;

import org.atlasapi.persistence.messaging.event.EntityUpdatedEvent;

/**
 * Base interface for workers that need to process {@link org.atlasapi.persistence.messaging.event.Event}s.
 */
public interface Worker {
    
    /**
     * Process a {@link org.atlasapi.persistence.messaging.event.EntityUpdatedEvent}.
     */
    void process(EntityUpdatedEvent command);
}
