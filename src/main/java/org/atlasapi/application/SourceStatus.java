package org.atlasapi.application;

import com.google.common.base.Preconditions;

public class SourceStatus {

    public static final SourceStatus UNAVAILABLE = new SourceStatus(SourceState.UNAVAILABLE, false);
    public static final SourceStatus REVOKED = new SourceStatus(SourceState.REVOKED, false);
    public static final SourceStatus AVAILABLE_ENABLED = new SourceStatus(SourceState.AVAILABLE, true);
    public static final SourceStatus AVAILABLE_DISABLED = new SourceStatus(SourceState.AVAILABLE, false);
    
    public enum SourceState {
        
        UNAVAILABLE,
        REQUESTED,
        AVAILABLE,
        REVOKED
        
    }
    
    private final SourceState state;
    private final boolean enabled;

    private SourceStatus(SourceState state, boolean enabled) {
        this.state = state;
        this.enabled = enabled;
    }
    
    public SourceStatus copyWithState(SourceState state) {
        return new SourceStatus(state, enabled);
    }
    
    public SourceStatus enable() {
        Preconditions.checkState(state == SourceState.AVAILABLE);
        return new SourceStatus(state, true);
    }
    
    public SourceStatus disable() {
        return new SourceStatus(state, false);
    }
}
