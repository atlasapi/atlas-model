package org.atlasapi.media.entity.simple;


public class Segment extends Description {

    private String segmentType;
    private Integer duration;

    public String getSegmentType() {
        return this.segmentType;
    }

    public Integer getDuration() {
        return this.duration;
    }

    @Override
    public Segment copy() {
        Segment copy = new Segment();
        this.copyTo(copy);
        copy.setDuration(this.getDuration());
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
