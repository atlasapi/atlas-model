package org.atlasapi.persistence.content;

import java.util.List;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.media.common.Identified;
import org.atlasapi.search.model.SearchQuery;

public interface SearchResolver {

    List<Identified> search(SearchQuery query, ApplicationConfiguration appConfig);
    
}
