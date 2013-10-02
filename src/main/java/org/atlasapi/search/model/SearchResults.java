package org.atlasapi.search.model;

import java.util.List;

import org.atlasapi.media.entity.simple.ContentIdentifier;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;

public class SearchResults {

	private List<String> results;
	private List<ContentIdentifier> contentIdentifiers;
	
	public SearchResults() { /* for GSON */ }
	
	public SearchResults(Iterable<ContentIdentifier> contentIdentifiers) {
		this.results = ImmutableList.copyOf(Iterables.transform(contentIdentifiers, TO_URI));
		this.contentIdentifiers = ImmutableList.copyOf(contentIdentifiers);
	}

	@Deprecated
	public List<String> toUris() {
		return results;
	}
	
	public List<ContentIdentifier> contentIdentifiers() {
	    return contentIdentifiers;
	}
	
	private static Function<ContentIdentifier, String> TO_URI = new Function<ContentIdentifier, String>() {

        @Override
        public String apply(ContentIdentifier input) {
            return input.getUri();
        }
	    
	    
	};
}
