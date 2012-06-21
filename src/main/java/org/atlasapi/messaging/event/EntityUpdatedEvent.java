package org.atlasapi.messaging.event;

import org.atlasapi.messaging.worker.Worker;
import org.joda.time.DateTime;

/**
 * Event signaling that a given entity has been created or updated.
 */
public class EntityUpdatedEvent extends AbstractEvent {

    public EntityUpdatedEvent(String changeId, DateTime timestamp, String entityId, String entityType, String entitySource) {
        super(changeId, timestamp, entityId, entityType, entitySource);
    }

    @Override
    public void dispatchTo(Worker worker) {
        worker.process(this);
    }
}
