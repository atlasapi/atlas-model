package org.atlasapi.messaging;

/**
 */
public abstract class AbstractMessage implements Message {

    private final String messageId;
    private final Long timestamp;
    private final String entityId;
    private final String entityType;
    private final String entitySource;

    public AbstractMessage(String messageId, Long timestamp, String entityId, String entityType, String entitySource) {
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
    public Long getTimestamp() {
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

    @Override
    public boolean equals(Object o) {
        if (o instanceof Message) {
            Message other = (Message) o;
            return this.messageId.equals(other.getMessageId());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.messageId.hashCode();
    }
}
