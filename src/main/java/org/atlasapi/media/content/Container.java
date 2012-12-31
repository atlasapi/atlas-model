package org.atlasapi.media.content;

import java.util.Comparator;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

public class Container extends Content {

    private final static Comparator<Item> seriesAndEpisodeFallBack = new Comparator<Item>() {
        @Override
        public int compare(Item item1, Item item2) {
            if (item1 instanceof Episode && item2 instanceof Episode) {
                return compareEpisodes((Episode) item1, (Episode) item2);
            }
            
            int comparison = DESCENDING_LAST_UPDATED.compare(item1, item2);
            if (comparison == 0) {
                comparison = defaultOrder(item1, item2);
            }
            return comparison;
        }
        
        private int compareEpisodes(Episode item1, Episode item2) {
            if (item1.getSeriesNumber() == null && item2.getSeriesNumber() == null) {
                 return defaultOrder(item1, item2);
            }
            if (item1.getSeriesNumber() == null) {
                return 1;
            }
            if (item2.getSeriesNumber() == null) {
                return -1;
            }
            int seriesComparison = item1.getSeriesNumber().compareTo(item2.getSeriesNumber());
            if (seriesComparison != 0) {
                return seriesComparison;
            }
            return compareEpisodeNumbers(item1, item2);
            
        }

        private int compareEpisodeNumbers(Episode item1, Episode item2) {
            if (item1.getEpisodeNumber() == null && item2.getEpisodeNumber() == null) {
                 return defaultOrder(item1, item2);
            }
            if (item1.getEpisodeNumber() == null) {
                return 1;
            }
            if (item2.getEpisodeNumber() == null) {
                return -1;
            }
            int episodeComparison = item1.getEpisodeNumber().compareTo(item2.getEpisodeNumber());
            if (episodeComparison != 0) {
                return episodeComparison;
            }
            return defaultOrder(item1, item2);
        }

        private int defaultOrder(Item item1, Item item2) {
            return item1.getCanonicalUri().compareTo(item2.getCanonicalUri());
        }
    };
    //
    private static final Ordering<Identified> lastUpdatedOrdering = Ordering.from(DESCENDING_LAST_UPDATED);
    private static final Ordering<Item> seriesAndEpisodeOrdering = Ordering.from(seriesAndEpisodeFallBack);
	//
    protected ImmutableList<ChildRef> childRefs = ImmutableList.of();

	public Container(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
    
    public Container() {}
    
    @RdfProperty(uri="child",relation=true)
    public ImmutableList<ChildRef> getChildRefs() {
        return childRefs;
    }
    
    public void setChildRefs(Iterable<ChildRef> childRefs) {
        this.childRefs = ImmutableList.copyOf(childRefs);
    }
    
    public Container toSummary() {
        Container summary = new Container(this.getCanonicalUri(), this.getCurie(), this.getPublisher());
        summary.setTitle(this.getTitle());
        summary.setDescription(this.getDescription());
        return summary;
    }
    
    
        
    public Container copy() {
        Container copy = new Container();
        copyTo(this, copy);
        return copy;
    }
    
    public final static <T extends Item> void copyTo(Container from, Container to) {
        Content.copyTo(from, to);
        to.childRefs = ImmutableList.copyOf(from.childRefs);
    }    
}
