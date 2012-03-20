package org.atlasapi.persistence.content.listing;

import java.util.Iterator;

import org.atlasapi.media.entity.Content;
import org.atlasapi.media.entity.Publisher;
import org.joda.time.DateTime;

public interface LastUpdatedContentFinder {

    Iterator<Content> updatedSince(Publisher publisher, DateTime since);
    
}
