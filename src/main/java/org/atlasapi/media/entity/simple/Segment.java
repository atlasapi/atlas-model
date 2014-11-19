package org.atlasapi.media.entity.simple;

import org.atlasapi.media.entity.Described;

public class Segment extends Described {

    private String title;

    private String description;

    private String segmentType;
    private Integer duration;

    public String getTitle() {
        return this.title;
    }

    @Override
    public Described copy() {
        Segment copy = new Segment();
        this.copyTo(this, copy);
        copy.setDuration(this.duration);
        copy.setDescription(this.description);
        copy.setSegmentType(this.segmentType);
        copy.setTitle(this.title);
        return copy;
    }

    public String getDescription() {
        return this.description;
    }

    public String getSegmentType() {
        return this.segmentType;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSegmentType(String type) {
        this.segmentType = type;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
