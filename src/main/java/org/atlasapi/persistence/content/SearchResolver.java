package org.atlasapi.persistence.content;

import java.util.List;

import org.atlasapi.application.OldApplicationConfiguration;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.search.model.SearchQuery;

public interface SearchResolver {

    List<Identified> search(SearchQuery query, OldApplicationConfiguration appConfig);
    
}
