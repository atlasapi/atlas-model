package org.atlasapi.media.entity.simple;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import javax.xml.bind.annotation.XmlType;

@XmlType(name="eventref", namespace= PLAY_SIMPLE_XML.NS)
public class EventRef {

    private String id;
    private PublisherDetails publisher;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }
    public PublisherDetails getPublisher() {
        return publisher;
    }


}
