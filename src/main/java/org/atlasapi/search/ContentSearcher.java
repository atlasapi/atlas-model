package org.atlasapi.search;

import org.atlasapi.search.model.SearchQuery;
import org.atlasapi.search.model.SearchResults;

public interface ContentSearcher {

	SearchResults search(SearchQuery query);

}
