package org.atlasapi.media.entity;

public enum ImageType {
    PRIMARY("primary"),
    ADDITIONAL("additional"),
    BOX_ART("box_art"),
    POSTER("poster"),
    LOGO("logo"),
    GENERIC_IMAGE_CONTENT_PLAYER("generic_image_content_player"),
    GENERIC_IMAGE_CONTENT_ORIGINATOR("generic_image_content_originator")
    ;
    
    private final String name;
    
    private ImageType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
}
