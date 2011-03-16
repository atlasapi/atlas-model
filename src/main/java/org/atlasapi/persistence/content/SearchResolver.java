package org.atlasapi.persistence.content;

import java.util.List;

import org.atlasapi.media.entity.Identified;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.search.model.Search;

import com.metabroadcast.common.query.Selection;

public interface SearchResolver {

    List<Identified> search(Search search, Iterable<Publisher> publishers, Selection selection);
    
}
