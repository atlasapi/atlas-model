package org.uriplay.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.vocabulary.PLAY;

@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="brandSummary", namespace=PLAY.NS)
public class BrandSummary extends Identified {

	private String title;
	private String description;
	
	public BrandSummary() { /* required for XML/JSON tools */ }
	
	public BrandSummary(String uri) {
		super(uri);
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
