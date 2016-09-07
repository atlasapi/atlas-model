package org.atlasapi.media.entity.simple;

import javax.annotation.Nullable;

public class LocalizedTitle extends Localized {

    private String title;
    private String type;
    
    public LocalizedTitle() {}

    @Nullable
    public String getType() {
        return type;
    }

    public void setType(@Nullable String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
}
