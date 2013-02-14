package org.atlasapi.media.entity.simple;

import java.util.Date;

public class Image extends Identified {

    private String type;
    private String color;
    private String background;
    private Integer width;
    private Integer height;
    private String aspectRatio;
    private String mimeType;
    private Date availabilityStart;
    private Date availabilityEnd;
    
    public Image() {
    }
    
    public Image(String uri) {
        super(uri);
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
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public Date getAvailabilityStart() {
        return availabilityStart;
    }
    
    public void setAvailabilityStart(Date availabilityStart) {
        this.availabilityStart = availabilityStart;
    }
    
    public Date getAvailabilityEnd() {
        return availabilityEnd;
    }
    
    public void setAvailabilityEnd(Date availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(String aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}
