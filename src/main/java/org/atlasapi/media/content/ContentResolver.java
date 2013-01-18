package org.atlasapi.media.content;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Resolved;

import com.metabroadcast.common.collect.OptionalMap;

public interface ContentResolver {

    Resolved<Content> resolveIds(Iterable<Id> ids);
    
    OptionalMap<String, Content> resolveAliases(Iterable<String> aliases, Publisher source);
    
}
