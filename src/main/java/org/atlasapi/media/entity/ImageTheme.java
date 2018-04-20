package org.atlasapi.media.entity;

public enum ImageTheme {
    
    DARK_OPAQUE("dark_opaque"), 
    LIGHT_OPAQUE("light_opaque"), 
    DARK_TRANSPARENT("dark_transparent"),
    LIGHT_TRANSPARENT("light_transparent"),
    DARK_MONOCHROME("dark_monochrome"),
    LIGHT_MONOCHROME("light_monochrome"),
    YV_MONOCHROME("yv_monochrome"),
    BT_SPORT_COLOR_TRANSPARENT("bt_sport_color_transparent");
    
    private final String name;
    
    ImageTheme(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
