package org.atlasapi.media.entity;

import org.joda.time.DateTime;

public class Image extends Identified {

    public Image(String uri) {
        super(uri);
    }
    
    private Publisher publisher;
    private Integer height;
    private Integer width;
    private ImageType type;
    private String caption;
    private String copyright;
    private DateTime availability_start;
    private DateTime availability_end;
    
    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
    public Integer getHeight() {
        return height;
    }
    public void setHeight(Integer height) {
        this.height = height;
    }
    public Integer getWidth() {
        return width;
    }
    public void setWidth(Integer width) {
        this.width = width;
    }
    public ImageType getType() {
        return type;
    }
    public void setType(ImageType type) {
        this.type = type;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public String getCopyright() {
        return copyright;
    }
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    public DateTime getAvailability_start() {
        return availability_start;
    }
    public void setAvailability_start(DateTime availability_start) {
        this.availability_start = availability_start;
    }
    public DateTime getAvailability_end() {
        return availability_end;
    }
    public void setAvailability_end(DateTime availability_end) {
        this.availability_end = availability_end;
    }
}
