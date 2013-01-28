package org.atlasapi.media.entity;

public enum ImageAspectRatio {
    SIXTEEN_BY_NINE("16x9"),
    FOUR_BY_THREE("4x3");
    
    private final String name;
    
    private ImageAspectRatio(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
