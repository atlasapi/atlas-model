package org.atlasapi.search.model;

import java.util.Set;

import org.atlasapi.media.content.Publisher;
import org.atlasapi.media.content.Specialization;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.metabroadcast.common.query.Selection;

public class SearchQuery {

	private final String term;
	private final Selection selection;
    private final Set<Specialization> includedSpecializations;
	private final Set<Publisher> includedPublishers;
    private final float titleWeighting;
    private final float broadcastWeighting;
    private final float catchupWeighting;

    public SearchQuery(String term, Selection selection, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this(term, selection, Sets.<Specialization>newHashSet(), Sets.<Publisher>newHashSet(), titleWeighting, broadcastWeighting, availabilityWeighting);
	}
    
	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this(term, selection, Sets.<Specialization>newHashSet(), includedPublishers, titleWeighting, broadcastWeighting, availabilityWeighting);
	}
    
    public SearchQuery(String term, Selection selection, Iterable<Specialization> includedSpecializations, Iterable<Publisher> includedPublishers, float titleWeighting, float broadcastWeighting, float availabilityWeighting) {
		this.term = term;
		this.selection = selection;
        this.titleWeighting = titleWeighting;
        this.broadcastWeighting = broadcastWeighting;
        this.catchupWeighting = availabilityWeighting;
        this.includedSpecializations = ImmutableSet.copyOf(includedSpecializations);
		this.includedPublishers = ImmutableSet.copyOf(includedPublishers);
	}
	
	public String getTerm() {
		return term;
	}
	
	public Selection getSelection() {
		return selection;
	}
    
    public Set<Specialization> getIncludedSpecializations() {
        return includedSpecializations;
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
