package org.uriplay.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.TransportType;
import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="item", namespace=PLAY.NS)
public class Item extends Description {

	private String publisher;
	private String image;
	private String thumbnail;
	
	private Integer episodeNumber;
	private Integer seriesNumber;
	
	private Set<String> genres = Sets.newHashSet();
	private Set<String> tags = Sets.newHashSet();
	
	private Set<String> containedIn = Sets.newHashSet();
	private Set<Location> locations = Sets.newHashSet();
	
	private Playlist brand;
	
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
	
	public void addLocation(Location location) {
		locations .add(location);
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="locations")
	@XmlElement(namespace=PLAY.NS, name="location")
	public Set<Location> getLocations() {
		return locations;
	}
	
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
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

	public Integer getEpisodeNumber() {
		return episodeNumber;
	}

	public void setEpisodeNumber(Integer episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	public Integer getSeriesNumber() {
		return seriesNumber;
	}

	public void setSeriesNumber(Integer seriesNumber) {
		this.seriesNumber = seriesNumber;
	}
	
	public boolean isAvailable() {
		for (Location location : locations) {
			if (location.isAvailable()) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isEmbeddable() {
		if (locations == null) {
			return false;
		}
		for (Location location : locations) {
			String transportType = location.getTransportType();
			if (transportType != null && TransportType.EMBEDOBJECT.toString().toLowerCase().equals(transportType.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@XmlElement(namespace=PLAY.NS, name="brand")
	public Playlist getBrand() {
		return brand;
	}
	
	public void setBrand(Playlist brand) {
		this.brand = brand;
	}

}
