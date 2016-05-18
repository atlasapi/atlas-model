package org.atlasapi.media.channel;

import java.util.Set;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.Sets;
import org.joda.time.LocalDate;

public class Masterbrand extends Identified implements ScheduleResolvable {

    private String uri;
    private String title;
    private String key;
    private Publisher source;
    private Publisher broadcaster;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private Set<TemporalField<Image>> images = Sets.newHashSet();
    private LocalDate startDate;
    private LocalDate endDate;

    public Masterbrand(String uri, String title, String key, Publisher source, Publisher broadcaster,
            String shortDescription, String mediumDescription, String longDescription,
            Set<TemporalField<Image>> images, LocalDate startDate, LocalDate endDate) {
        this.uri = uri;
        this.title = title;
        this.key = key;
        this.shortDescription = shortDescription;
        this.mediumDescription = mediumDescription;
        this.longDescription = longDescription;
        this.images = images;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static final Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String uri;
        private String title;
        private String key;
        private Publisher source;
        private Publisher broadcaster;
        private String shortDescription;
        private String mediumDescription;
        private String longDescription;
        private Set<TemporalField<Image>> images = Sets.newHashSet();
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder withUri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withKey(String key) {
            this.key = key;
            return this;
        }

        public Builder withSource(Publisher publisher) {
            this.source = publisher;
            return this;
        }

        public Builder withBroadcaster(Publisher broadcaster) {
            this.broadcaster = broadcaster;
            return this;
        }

        public Builder withShortDescription(String shortDescription) {
            this.shortDescription = shortDescription;
            return this;
        }

        public Builder withMediumDescription(String mediumDescription) {
            this.mediumDescription = mediumDescription;
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }

        public Builder withImage(Image image) {
            return withImage(image, null, null);
        };

        public Builder withImage(Image image, LocalDate startDate) {
            return withImage(image, startDate, endDate);
        }

        public Builder withImage(Image image, LocalDate startDate, LocalDate endDate) {
            this.images.add(new TemporalField<Image>(image, startDate, endDate));
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withEndDate(LocalDate localDate) {
            this.endDate = endDate;
            return this;
        }

        public Masterbrand build() {
            return new Masterbrand(uri, title, key, source, broadcaster,
                    shortDescription, mediumDescription,
                    longDescription, images, startDate,
                    endDate);
        }
    }

    public Publisher getSource() {
        return source;
    }

    public void setSource(Publisher source) {
        this.source = source;
    }

    public Publisher getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(Publisher broadcaster) {
        this.broadcaster = broadcaster;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getMediumDescription() {
        return mediumDescription;
    }

    public void setMediumDescription(String mediumDescription) {
        this.mediumDescription = mediumDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public Set<TemporalField<Image>> getImages() {
        return images;
    }

    public void addImage(Image image) {
        addImage(image, null, null);
    }

    public void addImage(Image image, LocalDate startDate) {
        addImage(image, startDate, null);
    }

    public void addImage(Image image, LocalDate startDate, LocalDate endDate) {
        this.images.add(new TemporalField<Image>(image, startDate, endDate));
    }

    public void addImage(TemporalField<Image> image) {
        this.images.add(image);
    }

    public void setImages(Iterable<TemporalField<Image>> images) {
        this.images = Sets.newHashSet(images);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
