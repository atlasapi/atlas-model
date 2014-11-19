package org.atlasapi.media.entity.simple;


public class Segment extends Description {

    private String title;

    private String description;

    private String segmentType;
    private Integer duration;

    public String getTitle() {
        return this.title;
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

    @Override
    public Description copy() {
        Segment copy = new Segment();
        this.copyTo(copy);
        copy.setDescription(this.getDescription());
        copy.setDuration(this.getDuration());
        copy.setTitle(this.title);
        copy.setSegmentType(this.segmentType);
        return copy;
    }

    public void setSegmentType(String type) {
        this.segmentType = type;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
