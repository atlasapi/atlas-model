package org.atlasapi.media.entity;

import javax.annotation.Nullable;

public class LocalizedTitle extends Localized {

    private String title;
    private String type;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalizedTitle)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        LocalizedTitle that = (LocalizedTitle) o;
        return java.util.Objects.equals(title, that.title) &&
                java.util.Objects.equals(type, that.type) &&
                super.equals(that);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), title, type);
    }

    @Override
    public Localized copy() {
        LocalizedTitle copy = new LocalizedTitle();
        Localized.copyTo(this, copy);
        
        copy.title = this.title;
        copy.type = this.type;
        
        return copy;
    }
    
}
