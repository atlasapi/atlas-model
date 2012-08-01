package org.atlasapi.messaging;

import org.atlasapi.messaging.worker.Worker;
import org.joda.time.DateTime;

/**
 * Message signaling the beginning of a messages replay.
 */
public class BeginReplayMessage extends AbstractMessage {

    public BeginReplayMessage(String messageId, DateTime timestamp, String entityId, String entityType, String entitySource) {
        super(messageId, timestamp, entityId, entityType, entitySource);
    }

    @Override
    public void dispatchTo(Worker worker) {
        worker.process(this);
    }
}
