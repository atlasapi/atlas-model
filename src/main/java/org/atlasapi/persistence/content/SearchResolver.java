package org.atlasapi.persistence.content;

import java.util.List;

import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.media.entity.Identified;
import org.atlasapi.search.model.SearchQuery;

public interface SearchResolver {

    List<Identified> search(SearchQuery query, Application application);
    
}
