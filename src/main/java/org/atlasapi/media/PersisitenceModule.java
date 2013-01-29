package org.atlasapi.media;

import org.atlasapi.media.content.ContentStore;


public interface PersisitenceModule {

    ContentStore contentStore();
    
}
