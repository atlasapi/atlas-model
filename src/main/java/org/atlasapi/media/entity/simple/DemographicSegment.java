package org.atlasapi.media.entity.simple;

public class DemographicSegment {

    private String key;
    private String label;
    private float value;
    
    public DemographicSegment() {
        
    }
    
    public DemographicSegment(String key, String label, float value) {
        this.key = key;
        this.label = label;
        this.value = value;
    }
    
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    public String getLabel() {
        return label;
    }

    
    public void setLabel(String label) {
        this.label = label;
    }

    public float getValue() {
        return value;
    }
    
    public void setValue(float value) {
        this.value = value;
    }
    
}
