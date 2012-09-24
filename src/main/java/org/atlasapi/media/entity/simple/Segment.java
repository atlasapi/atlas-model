package org.atlasapi.media.entity.simple;

public class Segment extends Identified {

    private String title;

    private String description;

    private String type;
    private Integer duration;
    private String id;

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
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

    public void setDescription(String description) {
        this.description = description;
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
