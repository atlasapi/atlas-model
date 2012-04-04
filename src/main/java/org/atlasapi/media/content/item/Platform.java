package org.atlasapi.media.content.item;

public enum Platform {
    XBOX;

    public String key() {
        return name().toLowerCase();
    }

    public static Platform fromKey(String key) {
        for(Platform platform : values()) {
            if(platform.key().equals(key)) {
                return platform;
            }
        }
        return null;
    }
}
