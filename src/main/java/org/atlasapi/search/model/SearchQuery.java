package org.atlasapi.search.model;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.query.Selection;

public class SearchQuery {

	private final String term;
	private final Selection selection;
	private final Set<Publisher> includedPublishers;
    private final float titleWeighting;
    private final float broadcastWeighting;
    private final float catchupWeighting;

	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this.term = term;
		this.selection = selection;
        this.titleWeighting = titleWeighting;
        this.broadcastWeighting = broadcastWeighting;
        this.catchupWeighting = availabilityWeighting;
		this.includedPublishers = ImmutableSet.copyOf(includedPublishers);
	}
	
	public String getTerm() {
		return term;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public Set<Publisher> getIncludedPublishers() {
		return includedPublishers;
	}

    public float getTitleWeighting() {
        return titleWeighting;
    }

    public float getBroadcastWeighting() {
        return broadcastWeighting;
    }

    public float getCatchupWeighting() {
        return catchupWeighting;
    }
}
