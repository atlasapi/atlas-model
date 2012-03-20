package org.atlasapi.persistence.content.listing;

import java.util.Iterator;

import org.atlasapi.media.entity.Content;

public interface ContentLister {

    Iterator<Content> listContent(ContentListingCriteria criteria);
    
}
