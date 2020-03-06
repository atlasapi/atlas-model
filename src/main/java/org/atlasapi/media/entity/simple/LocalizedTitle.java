package org.atlasapi.media.entity.simple;

import com.google.common.base.Objects;

public class LocalizedTitle extends Localized {

    private String title;

    public LocalizedTitle() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }

        if (that == null || !(that instanceof LocalizedTitle)) {
            return false;
        }

        LocalizedTitle thatTitle = (LocalizedTitle) that;

        return super.equals(that) && Objects.equal(this.title, thatTitle.title);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), title);
    }
}
