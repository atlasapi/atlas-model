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
	
	private Set<Playlist> containedIn = Sets.newHashSet();
	private Set<Location> locations = Sets.newHashSet();
	
	@XmlElementWrapper(namespace=PLAY.NS, name="containedIn")
	@XmlElement(namespace=PLAY.NS, name="list")
	public Set<Playlist> getContainedIn() {
		return containedIn;
	}
	
	public void addContainedIn(Playlist list) {
		containedIn .add(list);
	}
	
	public void setContainedIn(Set<Playlist> containedIn) {
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

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

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

	public Playlist primaryBrand() {
		Set<Playlist> playlists = getContainedIn();
		if (playlists == null) {
			return null;
		}
		for (Playlist playlist : playlists) {
			if (isABrand(playlist)) {
				return playlist;
			}
		}
		return null;
	}

	/**
	 * FIXME There is currently no way using the simple XML 
	 * format to work out if a playlist is also a brand.  This is
	 * a stop-gap solution and is _very_ fragile; 
	 */
	private boolean isABrand(Playlist playlist) {
		return playlist.getCurie() != null;
	}

}
