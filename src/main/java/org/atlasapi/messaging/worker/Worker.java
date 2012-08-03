package org.atlasapi.messaging.worker;

import org.atlasapi.messaging.EntityUpdatedMessage;

/**
 * Base interface for workers that need to process {@link org.atlasapi.persistence.messaging.Message}s.
 */
public interface Worker {
    
    /**
     * Process a {@link org.atlasapi.persistence.messaging.EntityUpdatedMessage}.
     */
    void process(EntityUpdatedMessage command);
}
