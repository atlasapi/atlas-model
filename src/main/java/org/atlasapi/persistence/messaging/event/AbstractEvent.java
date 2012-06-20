package org.atlasapi.persistence.messaging.event;

/**
 */
public abstract class AbstractEvent implements Event {
    
    private final String changeId;
    private final String entityId;
    private final String entityType;
    private final String entitySource;

    public AbstractEvent(String changeId, String entityId, String entityType, String entitySource) {
        this.changeId = changeId;
        this.entityId = entityId;
        this.entityType = entityType;
        this.entitySource = entitySource;
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
    
    @Override
    public String getEntitySource() {
        return entitySource;
    }
    
}
