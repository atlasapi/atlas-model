package org.atlasapi.media.entity.simple;

import java.util.Set;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.TransportType;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="item", namespace=PLAY_SIMPLE_XML.NS)
public class Item extends Description {

	private Integer episodeNumber;
	private Integer seriesNumber;
		
	private Set<Location> locations = Sets.newHashSet();

	private SortedSet<Broadcast> broadcasts = Sets.newTreeSet();
	
	private BrandSummary brandSummary;
	private SeriesSummary seriesSummary;
	
	public Item() { /* required for XML/JSON tools */ }
	
	public Item(String uri) {
		super(uri);
	}
	
	public void addLocation(Location location) {
		locations .add(location);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="locations")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="location")
	public Set<Location> getLocations() {
		return locations;
	}
	
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
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
			if (transportType != null && TransportType.EMBED.toString().toLowerCase().equals(transportType.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="brandSummary")
	public BrandSummary getBrandSummary() {
		return brandSummary;
	}
	
	public void setBrandSummary(BrandSummary brand) {
		this.brandSummary = brand;
	}

	public void addBroadcast(Broadcast broadcast) {
		broadcasts.add(broadcast);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="broadcasts")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="broadcast")
	public SortedSet<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(SortedSet<Broadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}

	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="seriesSummary")
	public SeriesSummary getSeriesSummary() {
		return seriesSummary;
	}
	
	public void setSeriesSummary(SeriesSummary seriesSummary) {
		this.seriesSummary = seriesSummary;
	}
}
