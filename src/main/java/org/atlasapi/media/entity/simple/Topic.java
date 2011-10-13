package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="topic", namespace=PLAY_SIMPLE_XML.NS)
public class Topic extends Description {

    private String type;
    private String namespace;
    private String value;
    
    public Topic() {}
    
    @Override
    public Topic copy() {
        Topic topic = new Topic();
        copyTo(topic);
        topic.setType(type);
        topic.setNamespace(namespace);
        topic.setValue(value);
        return null;
    }

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

}
