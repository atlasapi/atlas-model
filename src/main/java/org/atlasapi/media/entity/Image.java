package org.atlasapi.media.entity;

import org.joda.time.DateTime;

import com.google.common.base.Predicate;
import com.metabroadcast.common.media.MimeType;

public class Image extends Identified {
    
    public static final Predicate<Image> IS_PRIMARY = new Predicate<Image>() {
        @Override
        public boolean apply(Image input) {
            return input.getType() != null && input.getType().equals(ImageType.PRIMARY);
        }
    };
    
    public static final Builder builder(Image base) {
        Builder builder = new Builder(base.getCanonicalUri());
        builder.withHeight(base.height);
        builder.withWidth(base.width);
        builder.withType(base.type);
        builder.withColor(base.color);
        builder.withTheme(base.theme);
        builder.withAspectRatio(base.aspectRatio);
        builder.withMimeType(base.mimeType);
        builder.withAvailabilityStart(base.availabilityStart);
        builder.withAvailabilityEnd(base.availabilityEnd);
        builder.withHasTitleArt(base.hasTitleArt);
        return builder;
    }
    
    public static final Builder builder(String uri) {
        return new Builder(uri);
    }
    
    public static final class Builder {

        private String uri;
        private Integer height;
        private Integer width;
        private ImageType type;
        private ImageColor color;
        private ImageTheme theme;
        private ImageAspectRatio aspectRatio;
        private MimeType mimeType;
        private DateTime availabilityStart;
        private DateTime availabilityEnd;
        private boolean hasTitleArt;

        public Builder(String uri) {
            this.uri = uri;
        }
        
        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }
        
        public Builder withHeight(Integer height) {
            this.height = height;
            return this;
        }
        
        public Builder withWidth(Integer width) {
            this.width = width;
            return this;
        }
        
        public Builder withType(ImageType type) {
            this.type = type;
            return this;
        }
        
        public Builder withColor(ImageColor color) {
            this.color = color;
            return this;
        }
        
        public Builder withTheme(ImageTheme theme) {
            this.theme = theme;
            return this;
        }
        
        public Builder withAspectRatio(ImageAspectRatio aspectRatio) {
            this.aspectRatio = aspectRatio;
            return this;
        }
        
        public Builder withMimeType(MimeType mimeType) {
            this.mimeType = mimeType;
            return this;
        }
        
        public Builder withAvailabilityStart(DateTime availabilityStart) {
            this.availabilityStart = availabilityStart;
            return this;
        }
        
        public Builder withAvailabilityEnd(DateTime availabilityEnd) {
            this.availabilityEnd = availabilityEnd;
            return this;
        }

        public Builder withHasTitleArt(boolean hasTitleArt) {
            this.hasTitleArt = hasTitleArt;
            return this;
        }

        public Image build() {
            Image image = new Image(uri);
            image.setHeight(height);
            image.setWidth(width);
            image.setType(type);
            image.setColor(color);
            image.setTheme(theme);
            image.setAspectRatio(aspectRatio);
            image.setMimeType(mimeType);
            image.setAvailabilityStart(availabilityStart);
            image.setAvailabilityEnd(availabilityEnd);
            image.setHasTitleArt(hasTitleArt);
            return image;
        }
    }

    public Image(String uri) {
        super(uri);
    }
    
    private ImageType type;
    private ImageColor color;
    private ImageTheme theme;
    private Integer height;
    private Integer width;
    private ImageAspectRatio aspectRatio;
    private MimeType mimeType;
    private DateTime availabilityStart;
    private DateTime availabilityEnd;
    private boolean hasTitleArt;
    
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

    public ImageTheme getTheme() {
        return theme;
    }

    public void setTheme(ImageTheme theme) {
        this.theme = theme;
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

    public boolean hasTitleArt() {
        return hasTitleArt;
    }

    public void setHasTitleArt(boolean hasTitleArt) {
        this.hasTitleArt = hasTitleArt;
    }

    public static final Predicate<Image> IS_AVAILABLE = new Predicate<Image>() {
        @Override
        public boolean apply(Image input) {
            return (input.getAvailabilityStart() == null || ! (new DateTime(input.getAvailabilityStart()).isAfterNow()))
                    && (input.getAvailabilityEnd() == null || new DateTime(input.getAvailabilityEnd()).isAfterNow());
        }
    };
}
