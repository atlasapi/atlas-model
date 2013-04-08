package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.ContentGroup;

public interface ContentGroupLister {
    
    Iterable<ContentGroup> findAll();
    
}