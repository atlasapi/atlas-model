package org.atlasapi.media.entity;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.google.common.base.Preconditions.checkNotNull;

public class Provider {

    private String name;
    private String iconUrl;

    public Provider() {
    }

    public Provider(@Nonnull String name, @Nullable String iconUrl) {
        this.name = checkNotNull(name);
        this.iconUrl = iconUrl;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = checkNotNull(name);
    }

    @Nullable
    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Provider provider = (Provider) o;
        return name.equals(provider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Provider{" +
                "name='" + name + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }
}
