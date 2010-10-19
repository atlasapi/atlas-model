package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="publisher", namespace=PLAY_SIMPLE_XML.NS)
public class PublisherDetails {

	private String key;
	private String name;
	private String country;
	
	public PublisherDetails() { /* for JSON and XML tools */ }
	
	public PublisherDetails(String uri) {
		this.key = uri;
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setKey(String uri) {
		this.key = uri;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
}
