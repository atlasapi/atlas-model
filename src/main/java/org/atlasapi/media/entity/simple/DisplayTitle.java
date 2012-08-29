package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="displayTitle", namespace=PLAY_SIMPLE_XML.NS)
public class DisplayTitle {

	private String title;
	private String subtitle;
	
	public DisplayTitle() { /* required for XML/JSON tools */ }

    public DisplayTitle(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public DisplayTitle copy() {
        return new DisplayTitle(this.title, this.subtitle);
    }
}
