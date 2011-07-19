package org.atlasapi.search;

import org.atlasapi.search.model.SearchQuery;

public interface DebuggableContentSearcher extends ContentSearcher {
    
    String debug(SearchQuery query);
}
