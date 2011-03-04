package org.atlasapi.media.entity;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class Container<T extends Item> extends Content implements MutableContentList<T> {

	protected ImmutableList<T> contents = ImmutableList.of();
	protected final Ordering<Identified> lastUpdatedOrdering = Ordering.from(DESCENDING_LAST_UPDATED);

	public Container(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
    
    public Container() {}

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public ImmutableList<T> getContents() {
		return contents;
	}
    
    public List<String> getContentUris() {
    	return Lists.transform(contents, Identified.TO_URI);
    }
    
    public final void setContents(Iterable<? extends T> contents) {
		Set<T> deduped = ImmutableSet.copyOf(lastUpdatedOrdering.immutableSortedCopy(contents));
        this.contents = ImmutableList.copyOf(seriesAndEpisodeOrdering.immutableSortedCopy(deduped));
		for (T content : this.contents) {
			contentAdded(content);
		}
    }
    
    public final void setContents(T... contents) {
    	setContents(ImmutableList.copyOf(contents));
    }

    protected void contentAdded(T content) {
    	content.setContainer(this);
	}
    
	@SuppressWarnings("unchecked")
	public void addOrReplace(Item item) {
		if (!getContents().contains(item)) {
			// add
			((Container<Item>) this).addContents(item);
		} else { 
			// replace
			List<Item> currentItems = Lists.<Item>newArrayList(getContents());
			currentItems.set(currentItems.indexOf(item), item);
			((Container<Item>) this).setContents(currentItems);
		}
	}

	public final void addContents(T... contents) {
    	addContents(ImmutableList.copyOf(contents));
    }
    
    @Override
	public void addContents(Iterable<? extends T> contents) {
		setContents(Iterables.concat(this.contents, contents));
    }
    
    public Container<T> toSummary() {
        Container<T> summary = new Container<T>(this.getCanonicalUri(), this.getCurie(), this.getPublisher());
        summary.setTitle(this.getTitle());
        summary.setDescription(this.getDescription());
        return summary;
    }
    
    private final Comparator<T> seriesAndEpisodeFallBack = new Comparator<T>() {
        @Override
        public int compare(T item1, T item2) {
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
    
    protected final Ordering<T> seriesAndEpisodeOrdering = Ordering.from(seriesAndEpisodeFallBack);
}
