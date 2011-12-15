package org.atlasapi.media.entity.simple;

public class Segment extends Identified {

    private String title;

    private String shortSynopsis;
    private String mediumSynopsis;
    private String longSynopsis;

    private String type;
    private Integer duration;
    private String id;

    public String getTitle() {
        return this.title;
    }

    public String getShortSynopsis() {
        return this.shortSynopsis;
    }

    public String getMediumSynopsis() {
        return this.mediumSynopsis;
    }

    public String getLongSynopsis() {
        return this.longSynopsis;
    }

    public String getType() {
        return this.type;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getId() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public void setMediumSynopsis(String mediumSynopsis) {
        this.mediumSynopsis = mediumSynopsis;
    }

    public void setLongSynopsis(String longSynopsis) {
        this.longSynopsis = longSynopsis;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setId(String id) {
        this.id = id;
    }

}
