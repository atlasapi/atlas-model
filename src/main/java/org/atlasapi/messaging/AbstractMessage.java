package org.atlasapi.messaging;

import org.joda.time.DateTime;

/**
 */
public abstract class AbstractMessage implements Message {
    
    private final String messageId;
    private final DateTime timestamp;
    private final String entityId;
    private final String entityType;
    private final String entitySource;

    public AbstractMessage(String messageId, DateTime timestamp, String entityId, String entityType, String entitySource) {
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.entityId = entityId;
        this.entityType = entityType;
        this.entitySource = entitySource;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }
    
    @Override
    public DateTime getDateTime() {
        return timestamp;
    }

    @Override
    public String getEntityId() {
        return entityId;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }
    
    @Override
    public String getEntitySource() {
        return entitySource;
    }
    
}
