package org.atlasapi.media.entity.simple;

public class SegmentEvent extends Identified {

    private Integer position;
    private Integer offset;
    private Boolean isChapter;
    
    private String title;

    private String shortSynopsis;
    private String mediumSynopsis;
    private String longSynopsis;

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

    public String getShortSynopsis() {
        return this.shortSynopsis;
    }

    public void setShortSynopsis(String shortSynopsis) {
        this.shortSynopsis = shortSynopsis;
    }

    public String getMediumSynopsis() {
        return this.mediumSynopsis;
    }

    public void setMediumSynopsis(String mediumSynopsis) {
        this.mediumSynopsis = mediumSynopsis;
    }

    public String getLongSynopsis() {
        return this.longSynopsis;
    }

    public void setLongSynopsis(String longSynopsis) {
        this.longSynopsis = longSynopsis;
    }

    public Segment getSegment() {
        return this.segment;
    }

    public void setSegment(Segment segment) {
        this.segment = segment;
    }
    
}
