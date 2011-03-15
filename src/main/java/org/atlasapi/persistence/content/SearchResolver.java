package org.atlasapi.persistence.content;

import java.util.List;

import org.atlasapi.media.entity.Content;
import org.atlasapi.search.model.Search;

public interface SearchResolver {

    List<Content> search(Search search);
    
}
