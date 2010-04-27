package org.uriplay.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Sets;

/**
 * Description supertype for simple model.
 *  
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="description", namespace=PLAY.NS)
public class Description {

	private String uri;
	private Set<String> aliases = Sets.newHashSet();
	private String curie;
	
	private String title;
	private String description;
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setAliases(Set<String> aliases) {
		this.aliases = aliases;
	}
	
	@XmlElementWrapper( name="aliases")
	@XmlElement(name="alias")
	public Set<String> getAliases() {
		return aliases;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setCurie(String curie) {
		this.curie = curie;
	}

	public String getCurie() {
		return curie;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode() {
		if (uri == null) {
			return super.hashCode();
		}
		return uri.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (uri != null && obj instanceof Description) {
			return uri.equals(((Description) obj).uri);
		}
		return false;
	}
}
