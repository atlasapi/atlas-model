package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.TransportType;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Country;

@XmlType(name="item", namespace=PLAY_SIMPLE_XML.NS)
public class Item extends Description {

	private Integer episodeNumber;
	private Integer seriesNumber;
    
    private String isrc;
    private Long duration;
		
	private Set<Location> locations = Sets.newHashSet();

	private SortedSet<Broadcast> broadcasts = Sets.newTreeSet();
    private List<SegmentEvent> segmentEvents;
	
	private SeriesSummary seriesSummary;
	
	private Boolean special;
	private Boolean blackAndWhite;
	private Set<Country> countriesOfOrigin = Sets.newHashSet();
    private Set<Subtitles> subtitles = Sets.newHashSet();
    private Set<ReleaseDate> releaseDates = Sets.newHashSet();
	            
	public Item() { /* required for XML/JSON tools */ }
	
	public Item(String uri) {
		super(uri);
	}
	
	public void addLocation(Location location) {
		locations.add(location);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="locations")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="location")
	public Set<Location> getLocations() {
		return locations;
	}
	
	public void setLocations(Iterable<Location> locations) {
		this.locations = Sets.newHashSet(locations);
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

	public void addBroadcast(Broadcast broadcast) {
		broadcasts.add(broadcast);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="broadcasts")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="broadcast")
	public SortedSet<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(Iterable<Broadcast> broadcasts) {
		this.broadcasts = Sets.newTreeSet(broadcasts);
	}

	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="seriesSummary")
	public SeriesSummary getSeriesSummary() {
		return seriesSummary;
	}
	
	public void setSeriesSummary(SeriesSummary seriesSummary) {
		this.seriesSummary = seriesSummary;
	}
	
	public void setSpecial(Boolean special) {
	    this.special = special;
	}
	
	public Boolean isSpecial() {
	    return special;
	}
	
	public void setBlackAndWhite(Boolean blackAndWhite) {
        this.blackAndWhite = blackAndWhite;
    }

    public Boolean isBlackAndWhite() {
        return blackAndWhite;
    }
    
    public void addCountryOfOrigin(Country country) {
        countriesOfOrigin.add(country);
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="countriesOfOrigin")
    @XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="country")
    public Set<Country> getCountriesOfOrigin() {
        return countriesOfOrigin;
    }
    
    public void setCountriesOfOrigin (Iterable<Country> countries) {
        this.countriesOfOrigin = Sets.newHashSet(countries);
    }
    
    public void addCountry(Country country) {
        countriesOfOrigin.add(country);
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    public String getIsrc() {
        return isrc;
    }
    
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getDuration() {
        return duration;
    }
	
	public Item copy() {
        Item copy = new Item();
        
        copyTo(copy);
        
        copy.setEpisodeNumber(getEpisodeNumber());
        copy.setSeriesNumber(getSeriesNumber());
        copy.setIsrc(getIsrc());
        copy.setDuration(duration);
        copy.setLocations(Iterables.transform(getLocations(), Location.TO_COPY));
        copy.setBroadcasts(Iterables.transform(getBroadcasts(), Broadcast.TO_COPY));
        
        if (getBrandSummary() != null) {
            copy.setBrandSummary(getBrandSummary().copy());
        }
        if (getSeriesSummary() != null) {
            copy.setSeriesSummary(getSeriesSummary().copy());
        }
        
        copy.setBlackAndWhite(isBlackAndWhite());
        copy.setCountriesOfOrigin(getCountriesOfOrigin());
        return copy;
    }
	
    public static final Predicate<Item> HAS_AVAILABLE_LOCATION = new Predicate<Item>() {
        @Override
        public boolean apply(Item input) {
            return !input.getLocations().isEmpty() && !Iterables.isEmpty(Iterables.filter(input.getLocations(), Location.IS_AVAILABLE));
        }
    };
    
    public static final Predicate<Item> HAS_UPCOMING_LOCATION = new Predicate<Item>() {
        @Override
        public boolean apply(Item input) {
            return !input.getLocations().isEmpty() 
                && !Sets.filter(input.getLocations(), Location.IS_UPCOMING).isEmpty();
        }
    };
    
    public static final Predicate<Item> HAS_CURRENT_OR_UPCOMING_BROADCAST = new Predicate<Item>() {
        @Override
        public boolean apply(Item input) {
            return !input.getBroadcasts().isEmpty() && !Iterables.isEmpty(Iterables.filter(input.getBroadcasts(), Broadcast.IS_CURRENT_OR_UPCOMING));
        }
    };
    
    public static final Function<Item, Item> TO_COPY = new Function<Item, Item>() {
        @Override
        public Item apply(Item input) {
            return input.copy();
        }
    };
    
    public static final Predicate<Item> IS_SCHEDULE_MARKER = new Predicate<Item>() {
        @Override
        public boolean apply(Item input) {
            return input.isScheduleOnly();
        }
    };
    
    public static final Function<Item, Iterable<Person>> TO_PEOPLE = new Function<Item, Iterable<Person>>() {
        @Override
        public Iterable<Person> apply(Item input) {
            return input.getPeople();
        }
    };

    public void setSegments(List<SegmentEvent> segmentEvents) {
        this.segmentEvents = segmentEvents;
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="segmentEvents")
    @XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="segmentEvent")
    public List<SegmentEvent> getSegments() {
        return this.segmentEvents;
    }

    public void setSubtitles(Iterable<Subtitles> subtitles) {
        this.subtitles = Sets.newHashSet(subtitles);
    }

    public void setReleaseDates(Iterable<ReleaseDate> releaseDates) {
        this.releaseDates = Sets.newHashSet(releaseDates);
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="releaseDates")
    @XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="releaseDate")
    public Set<ReleaseDate> getReleaseDates() {
        return this.releaseDates;
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="subtitles")
    @XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="subtitle")
    public Set<Subtitles> getSubtitles() {
        return subtitles;
    }
}
