package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.metabroadcast.common.media.MimeType;

public class Image {
    
    public static final Predicate<Image> IS_PRIMARY = new Predicate<Image>() {
        @Override
        public boolean apply(Image input) {
            return input.getType() != null && input.getType().equals(ImageType.PRIMARY);
        }
    };

    private String uri;
    private ImageType type;
    private ImageColor color;
    private ImageBackground background;
    private Integer height;
    private Integer width;
    private ImageAspectRatio aspectRatio;
    private MimeType mimeType;
    private DateTime availabilityStart;
    private DateTime availabilityEnd;
    
    public Image(String uri) {
        this.uri = checkNotNull(uri);
    }
    
    public String getCanonicalUri() {
        return uri;
    }
    
    public void setCanonicalUri(String uri) {
        this.uri = uri;
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

    public ImageColor getColor() {
        return color;
    }

    public void setColor(ImageColor color) {
        this.color = color;
    }

    public ImageBackground getBackground() {
        return background;
    }

    public void setBackground(ImageBackground background) {
        this.background = background;
    }

    public ImageAspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(ImageAspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public MimeType getMimeType() {
        return mimeType;
    }

    public void setMimeType(MimeType mimeType) {
        this.mimeType = mimeType;
    }
    
    public DateTime getAvailabilityStart() {
        return availabilityStart;
    }
    
    public void setAvailabilityStart(DateTime availabilityStart) {
        this.availabilityStart = availabilityStart;
    }
    
    public DateTime getAvailabilityEnd() {
        return availabilityEnd;
    }
    
    public void setAvailabilityEnd(DateTime availabilityEnd) {
        this.availabilityEnd = availabilityEnd;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Image) {
            Image other = (Image) that;
            return uri.equals(other.uri);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return uri.hashCode();
    }
    
    public static final Predicate<Image> IS_AVAILABLE = new Predicate<Image>() {
        @Override
        public boolean apply(Image input) {
            return (input.getAvailabilityStart() == null || ! (new DateTime(input.getAvailabilityStart()).isAfterNow()))
                    && (input.getAvailabilityEnd() == null || new DateTime(input.getAvailabilityEnd()).isAfterNow());
        }
    };

}
