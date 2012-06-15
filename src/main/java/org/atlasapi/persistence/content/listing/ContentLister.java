package org.atlasapi.persistence.content.listing;

import java.util.Iterator;

import org.atlasapi.media.content.Content;

public interface ContentLister {

    Iterator<Content> listContent(ContentListingCriteria criteria);
    
}
