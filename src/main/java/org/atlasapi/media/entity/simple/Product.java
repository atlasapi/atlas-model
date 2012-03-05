package org.atlasapi.media.entity.simple;

import java.util.Set;

public class Product extends Aliased {

    private String gtin;
    private String title;
    private String description;
    private String image;
    private String thumbnail;
    private Integer year;
    private Set<ProductLocation> locations;

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setLocations(Set<ProductLocation> locations) {
        this.locations = locations;
    }

    public String getGtin() {
        return this.gtin;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public Integer getYear() {
        return this.year;
    }

    public Set<ProductLocation> getLocations() {
        return this.locations;
    }

}
