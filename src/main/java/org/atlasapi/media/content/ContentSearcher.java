package org.atlasapi.media.content;

import com.google.common.util.concurrent.ListenableFuture;
import org.atlasapi.search.model.SearchQuery;
import org.atlasapi.search.model.SearchResults;

public interface ContentSearcher {
    
    ListenableFuture<SearchResults> search(SearchQuery query);
    
}
