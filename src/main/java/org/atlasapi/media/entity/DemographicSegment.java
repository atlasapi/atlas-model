package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

public class DemographicSegment {

    private final String key;
    private final String label;
    private final float value;
    
    public DemographicSegment(String key, String label, float value) {
        this.key = checkNotNull(key);
        this.label = checkNotNull(label);
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public float getValue() {
        return value;
    }
    
}
