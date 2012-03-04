package org.atlasapi.search.model;

import java.util.Set;

import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;
import com.metabroadcast.common.base.Maybe;
import com.metabroadcast.common.query.Selection;

public class SearchQuery {

	private final String term;
	private final Selection selection;
	private final Set<Publisher> includedPublishers;
    private final float titleWeighting;
    private final float broadcastWeighting;
    private final float catchupWeighting;
	private final Maybe<Float> priorityChannelWeighting;
	private final Maybe<Float> firstBroadcastWeighting;

	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting, Maybe<Float> priorityChannelWeighting, Maybe<Float> firstBroadcastWeighting) {
		this.term = term;
		this.selection = selection;
        this.titleWeighting = titleWeighting;
        this.broadcastWeighting = broadcastWeighting;
        this.catchupWeighting = availabilityWeighting;
		this.includedPublishers = ImmutableSet.copyOf(includedPublishers);
		this.priorityChannelWeighting = priorityChannelWeighting;
		this.firstBroadcastWeighting = firstBroadcastWeighting;
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
    
    public Maybe<Float> getPriorityChannelWeighting() {
    	return priorityChannelWeighting;
    }
    
    public Maybe<Float> getFirstBroadcastWeighting() {
    	return firstBroadcastWeighting;
    }
}
