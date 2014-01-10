package org.atlasapi.search;

import org.atlasapi.search.model.SearchQuery;

import com.google.common.base.Optional;

public interface DebuggableContentSearcher extends ContentSearcher {
    
    String debug(SearchQuery query);

    Optional<String> document(String key);
}
