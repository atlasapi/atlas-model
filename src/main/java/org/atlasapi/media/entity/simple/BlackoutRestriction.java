package org.atlasapi.media.entity.simple;


public class BlackoutRestriction {

    private Boolean all = false;
    
    public BlackoutRestriction(Boolean all) {
        this.all = all;
    }
    
    public Boolean getAll() {
        return all;
    }
    
    public void setAll(Boolean all) {
        this.all = all;
    }
}
