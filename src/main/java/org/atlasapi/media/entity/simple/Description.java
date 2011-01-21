package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Description supertype for simple model.
 *  
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="description", namespace=PLAY_SIMPLE_XML.NS)
public class Description extends Aliased {

    private String title;
	private String description;
	
	private PublisherDetails publisher;
	private String image;
	private String thumbnail;
	
	private Set<String> genres = Sets.newHashSet();
	private Set<String> tags = Sets.newHashSet();
	
	private Set<String> containedIn = Sets.newHashSet();
	
	private List<Item> clips = Lists.newArrayList();

	private Set<String> sameAs;

	private String mediaType;
	
	public Description(String uri) {
		super(uri);
	}
	
	public Description() { /* required for XML/JSON tools */	}

	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="genres")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="genre")
	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="tags")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="tag")
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="containedIn")
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
	
	@XmlElementWrapper(name="clips")
	@XmlElement(name="clip")
	public List<Item> getClips() {
		return clips;
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
		
	public void setClips(List<Item> clips) {
		this.clips = clips;
	}

	public void setSameAs(Set<String> sameAs) {
		this.sameAs = sameAs;
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="sameAs")
	@XmlElement(name="uri")
	public Set<String> getSameAs() {
		return sameAs;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	
	public String getMediaType() {
		return mediaType;
	}
}
