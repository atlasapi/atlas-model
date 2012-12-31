package org.atlasapi.media.content;

import com.google.common.collect.FluentIterable;
import org.atlasapi.media.entity.Publisher;

import com.metabroadcast.common.collect.OptionalMap;

public interface ContentResolver {

    FluentIterable<Content> resolveIds(Iterable<Long> ids);
    
    OptionalMap<String, Content> resolveAliases(Iterable<String> aliases, Publisher source);
    
}
