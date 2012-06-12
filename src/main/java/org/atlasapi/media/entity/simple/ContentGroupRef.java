package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlType(name="contentGroupRef", namespace=PLAY_SIMPLE_XML.NS)
public class ContentGroupRef {

	private Long id;
    private String uri;
	
	public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
