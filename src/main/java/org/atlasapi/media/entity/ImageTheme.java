package org.atlasapi.media.entity;

public enum ImageTheme {
    
    DARK_OPAQUE("dark opaque"), 
    LIGHT_OPAQUE("light opaque"), 
    DARK_TRANSPARENT("dark transparent"),
    LIGHT_TRANSPARENT("dark transparent");
    
    private final String name;
    
    private ImageTheme(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
