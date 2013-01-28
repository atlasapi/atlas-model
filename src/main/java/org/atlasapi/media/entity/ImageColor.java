package org.atlasapi.media.entity;

public enum ImageColor {
    COLOR("color"),
    BLACK_AND_WHITE("black_and_white"),
    SINGLE_COLOR("single_color");
    
    private final String name;
    
    private ImageColor(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
