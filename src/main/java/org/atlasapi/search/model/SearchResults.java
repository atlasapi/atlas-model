package org.atlasapi.search.model;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableSet;

public class SearchResults implements Iterable<Long> {

	private ImmutableSet<Long> results;

	public SearchResults() { /* for GSON */ }
	
	public SearchResults(Iterable<Long> uris) {
	    //ImmutableSet maintains insertion order
		this.results = ImmutableSet.copyOf(uris);
	}

	public List<Long> getIds() {
		return results.asList();
	}

    @Override
    public Iterator<Long> iterator() {
        return results.iterator();
    }
}
