package org.atlasapi.messaging.event;

import org.joda.time.DateTime;

/**
 */
public abstract class AbstractEvent implements Event {
    
    private final String changeId;
    private final DateTime timestamp;
    private final String entityId;
    private final String entityType;
    private final String entitySource;

    public AbstractEvent(String changeId, DateTime timestamp, String entityId, String entityType, String entitySource) {
        this.changeId = changeId;
        this.timestamp = timestamp;
        this.entityId = entityId;
        this.entityType = entityType;
        this.entitySource = entitySource;
    }

    @Override
    public String getChangeId() {
        return changeId;
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
