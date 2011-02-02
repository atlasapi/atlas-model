package org.atlasapi.search.model;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class SearchResults {

	private List<String> results;

	public SearchResults() { /* for GSON */ }
	
	public SearchResults(Iterable<String> uris) {
		this.results = ImmutableList.copyOf(uris);
	}

	public List<String> toUris() {
		return results;
	}
}
