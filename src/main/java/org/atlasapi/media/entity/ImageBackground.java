package org.atlasapi.media.entity;

public enum ImageBackground {
    BLACK("black"), 
    WHITE("white"), 
    TRANSPARENT("transparent");
    
    private final String name;
    
    private ImageBackground(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
