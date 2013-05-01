package org.atlasapi.media.entity;

public enum ImageType {
    PRIMARY("primary"),
    ALTERNATE("alternate"),
    BOX_ART("box_art"),
    POSTER("poster"),
    LOGO("logo");
    
    private final String name;
    
    private ImageType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
