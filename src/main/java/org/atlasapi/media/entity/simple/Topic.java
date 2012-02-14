package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="topic", namespace=PLAY_SIMPLE_XML.NS)
public class Topic extends Aliased {

    private String title;
    private String description;
    
    private PublisherDetails publisher;
    private String image;
    private String thumbnail;
    
    private Set<String> sameAs = Sets.newHashSet();
    
    private String type;
    private String namespace;
    private String value;
    
    public Topic() {}

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
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
    
    public PublisherDetails getPublisher() {
        return this.publisher;
    }

    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    
    public void setSameAs(Iterable<String> sameAs) {
        this.sameAs = Sets.newHashSet(sameAs);
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="sameAs")
    @XmlElement(name="uri")
    public Set<String> getSameAs() {
        return sameAs;
    }
}
