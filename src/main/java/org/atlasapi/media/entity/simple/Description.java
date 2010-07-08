package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.collect.ImmutableSet.Builder;

/**
 * Description supertype for simple model.
 *  
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="description", namespace=PLAY.NS)
public class Description extends Identified {

	private Set<String> aliases = Sets.newHashSet();
	
	private String title;
	private String description;
	
	private PublisherDetails publisher;
	private String image;
	private String thumbnail;
	
	private Set<String> genres = Sets.newHashSet();
	private Set<String> tags = Sets.newHashSet();
	
	private Set<String> containedIn = Sets.newHashSet();
	
	public Description(String uri) {
		super(uri);
	}
	
	public Description() { /* required for XML/JSON tools */	}

	@XmlElementWrapper(namespace=PLAY.NS, name="genres")
	@XmlElement(namespace=PLAY.NS, name="genre")
	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@XmlElementWrapper(namespace=PLAY.NS, name="tags")
	@XmlElement(namespace=PLAY.NS, name="tag")
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="containedIn")
	@XmlElement(name="uri")
	public Set<String> getContainedIn() {
		return containedIn;
	}
	
	public void addContainedIn(String playlistUri) {
		containedIn.add(playlistUri);
	}
	
	public void setContainedIn(Set<String> containedIn) {
		this.containedIn = containedIn;
	}
	
	public PublisherDetails getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherDetails publisher) {
		this.publisher = publisher;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ImmutableSet<String> identifiers() {
		Builder<String> ids = ImmutableSet.builder();
		ids.addAll(aliases);
		ids.add(uri);
		if (curie != null) {
			ids.add(curie);
		}
		return ids.build();
	}
}
