package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.channel.TemporalField;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.Sets;
import org.joda.time.LocalDate;

@XmlRootElement(namespace= PLAY_SIMPLE_XML.NS)
@XmlType(name="Masterbrand", namespace=PLAY_SIMPLE_XML.NS)
public class Masterbrand implements ScheduleResolvable {

    private String uri;
    private String title;
    private PublisherDetails source;
    private PublisherDetails broadcaster;
    private String shortDescription;
    private String mediumDescription;
    private String longDescription;
    private Set<Image> images = Sets.newHashSet();
    private LocalDate startDate;
    private LocalDate endDate;

    @XmlElement(name = "publisher")
    public PublisherDetails getSource() {
        return source;
    }

    public void setSource(PublisherDetails source) {
        this.source = source;
    }

    public PublisherDetails getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(PublisherDetails broadcaster) {
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

    public Set<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void setImages(Iterable<Image> images) {
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

}
