package org.atlasapi.messaging;

import org.atlasapi.messaging.worker.Worker;

/**
 * Message signaling that a given entity has been created or updated.
 */
public class EntityUpdatedMessage extends AbstractMessage {

    public EntityUpdatedMessage(String messageId, Long timestamp, String entityId, String entityType, String entitySource) {
        super(messageId, timestamp, entityId, entityType, entitySource);
    }

    @Override
    public void dispatchTo(Worker worker) {
        worker.process(this);
    }
}
