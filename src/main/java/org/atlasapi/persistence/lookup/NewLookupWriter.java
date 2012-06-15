package org.atlasapi.persistence.lookup;

import org.atlasapi.media.content.Content;

public interface NewLookupWriter {
    
    void ensureLookup(Content content);

}
