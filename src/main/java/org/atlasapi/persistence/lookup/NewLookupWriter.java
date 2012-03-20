package org.atlasapi.persistence.lookup;

import org.atlasapi.media.entity.Content;

public interface NewLookupWriter {
    
    void ensureLookup(Content content);

}
