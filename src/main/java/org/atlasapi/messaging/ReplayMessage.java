package org.atlasapi.messaging;

import org.atlasapi.messaging.worker.Worker;
import org.joda.time.DateTime;

/**
 * Message signaling the replay of a given source message.
 */
public class ReplayMessage extends AbstractMessage {

    private final Message original;
    
    public ReplayMessage(String messageId, DateTime timestamp, String entityId, String entityType, String entitySource, Message original) {
        super(messageId, timestamp, entityId, entityType, entitySource);
        this.original = original;
    }

    public Message getOriginal() {
        return original;
    }

    @Override
    public void dispatchTo(Worker worker) {
        worker.process(this);
    }
}
