package org.atlasapi.media.entity.simple;

import java.util.Date;

import org.atlasapi.media.entity.Publisher;

public class Image extends Identified {

    private PublisherDetails publisher;
    private Integer height;
    private Integer width;
    private String type;
    private String caption;
    private String copyright;
    private Date availability_start;
    private Date availability_end;
    private String format;
    
    public Image(String uri) {
        super(uri);
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setFormat(String format) {
        this.format = format;
    }
    
    public PublisherDetails getPublisher() {
        return publisher;
    }
    
    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }
    
    public Integer getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public Integer getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
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
    
    public Date getAvailability_start() {
        return availability_start;
    }
    
    public void setAvailability_start(Date availability_start) {
        this.availability_start = availability_start;
    }
    
    public Date getAvailability_end() {
        return availability_end;
    }
    
    public void setAvailability_end(Date availability_end) {
        this.availability_end = availability_end;
    }
  
}
