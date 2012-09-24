package org.atlasapi.media.entity.simple;

public class SegmentEvent extends Identified {

    private Integer position;
    private Integer offset;
    private Boolean isChapter;
    
    private String title;

    private String description;

    private Segment segment;

    public Integer getPosition() {
        return this.position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean getIsChapter() {
        return this.isChapter;
    }

    public void setIsChapter(Boolean isChapter) {
        this.isChapter = isChapter;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Segment getSegment() {
        return this.segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
    
}
