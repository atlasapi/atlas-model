package org.atlasapi.persistence.messaging.event;

/**
 */
public abstract class AbstractEvent implements Event{
    
    private final String changeId;
    private final String entityId;
    private final String entityType;

    public AbstractEvent(String changeId, String entityId, String entityType) {
        this.changeId = changeId;
        this.entityId = entityId;
        this.entityType = entityType;
    }

    @Override
    public String getChangeId() {
        return changeId;
    }

    @Override
    public String getEntityId() {
        return entityId;
    }

    @Override
    public String getEntityType() {
        return entityType;
    }
}
