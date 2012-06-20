package org.atlasapi.persistence.messaging.event;

import org.atlasapi.persistence.messaging.worker.Worker;

/**
 * Event signaling that a given entity has been created or updated.
 */
public class EntityUpdatedEvent extends AbstractEvent {

    public EntityUpdatedEvent(String changeId, String entityId, String entityType, String entitySource) {
        super(changeId, entityId, entityType, entitySource);
    }

    @Override
    public void dispatchTo(Worker worker) {
        worker.process(this);
    }
}
