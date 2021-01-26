package org.atlasapi.media.entity.simple;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.TransportType;
import org.atlasapi.media.entity.simple.ContentIdentifier.BrandIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.EpisodeIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.FilmIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.ItemIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.PersonIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.SeriesIdentifier;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Set;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="playlist", namespace=PLAY_SIMPLE_XML.NS)
public class Playlist extends Description {

	private List<ContentIdentifier> content = Lists.newArrayList();
    private Set<ContentIdentifier> upcomingContent = Sets.newHashSet();
    private Set<ContentIdentifier> availableContent = Sets.newHashSet();
    private Set<ContentIdentifier> recentContent = Sets.newHashSet();
    private List<SeriesIdentifier> series = Lists.newArrayList();
    private Set<Country> countriesOfOrigin = Sets.newHashSet();
	private Set<Location> locations = Sets.newHashSet();
    private Integer totalEpisodes;
    private Integer seriesNumber;

	public void add(ContentIdentifier c) {
		content.add(c);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="content")
	@XmlElements({ 
		@XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
		@XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
	})
	public List<ContentIdentifier> getContent() {
		return content;
	}
	
	public void setContent(Iterable<? extends ContentIdentifier> items) {
		this.content = Lists.newArrayList(items);
	}
	
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="seriesList")
    @XmlElements({ 
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
	})
    public List<SeriesIdentifier> getSeriesList() {
        return series;
    }

	@JsonProperty("series")
    public void setSeriesList(Iterable<? extends SeriesIdentifier> series) {
        this.series = Lists.newArrayList(series);
    }
	
	public Playlist copy() {
	    Playlist copy = new Playlist();
	    copyTo(copy);
	    copy.setContent(Iterables.transform(getContent(), ContentIdentifier.COPY));
        copy.upcomingContent = upcomingContent == null ? null : ImmutableSet.copyOf(Iterables.transform(getUpcomingContent(), ContentIdentifier.COPY));
        copy.availableContent = availableContent == null ? null : ImmutableSet.copyOf(Iterables.transform(getAvailableContent(), ContentIdentifier.COPY));
		copy.setLocations(Iterables.transform(getLocations(), Location.TO_COPY));
	    return copy;
	}
	
	public static final Function<Playlist, List<ContentIdentifier>> TO_CONTENTS = new Function<Playlist, List<ContentIdentifier>>() {
		@Override
		public List<ContentIdentifier> apply(Playlist input) {
			return input.getContent();
		}
	};

	public Integer getTotalEpisodes() {
	    return totalEpisodes;
	}
	
	public void setTotalEpisodes(Integer totalEpisodes) {
	    this.totalEpisodes = totalEpisodes;
	}
	
	public Integer getSeriesNumber() {
	    return seriesNumber;
	}
	
	public void setSeriesNumber(Integer seriesNumber) {
	    this.seriesNumber = seriesNumber;
	}

    public void setUpcomingContent(Iterable<ContentIdentifier> filteredRefs) {
        this.upcomingContent = ImmutableSet.copyOf(filteredRefs);
    }

    public Set<Country> getCountriesOfOrigin() {
		return ImmutableSet.copyOf(countriesOfOrigin);
	}

    public void setCountriesOfOrigin(Set<Country> countriesOfOrigin) {
		this.countriesOfOrigin = ImmutableSet.copyOf(countriesOfOrigin);
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

    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="upcoming_content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    public Set<ContentIdentifier> getUpcomingContent() {
        return upcomingContent;
    }
    
    public void setAvailableContent(Iterable<ContentIdentifier> filteredRefs) {
        this.availableContent = ImmutableSet.copyOf(filteredRefs);
    }

    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="available_content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    public Set<ContentIdentifier> getAvailableContent() {
        return availableContent;
    }

    public void setRecentContent(Iterable<ContentIdentifier> filteredRefs) {
        this.recentContent = ImmutableSet.copyOf(filteredRefs);
    }
    
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="recent_content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    public Set<ContentIdentifier> getRecentContent() {
        return recentContent;
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
}
