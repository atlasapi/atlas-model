package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlType(name = "equivalent", namespace = PLAY_SIMPLE_XML.NS)
public class SameAs {

    private String id;
    private String uri;
    
    public SameAs() {
        
    }
    
    public SameAs(String id, String uri) {
        this.id = id;
        this.uri = uri;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
}
