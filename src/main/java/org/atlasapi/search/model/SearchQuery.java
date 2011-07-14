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
    private final float currentnessWeighting;

	public SearchQuery(String term, Selection selection, Iterable<Publisher> includedPublishers, float titleWeighting, float currentnessWeighting) {
		this.term = term;
		this.selection = selection;
        this.titleWeighting = titleWeighting;
        this.currentnessWeighting = currentnessWeighting;
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

    public float getCurrentnessWeighting() {
        return currentnessWeighting;
    }
}
