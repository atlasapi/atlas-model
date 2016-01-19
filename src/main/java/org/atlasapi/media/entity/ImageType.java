package org.atlasapi.media.entity;

public enum ImageType {
    PRIMARY("primary"),
    ADDITIONAL("additional"),
    BOX_ART("box_art"),
    POSTER("poster"),
    LOGO("logo"),
    GENERIC("generic");
    
    private final String name;
    
    private ImageType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
