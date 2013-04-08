package org.atlasapi.media.content;

import org.atlasapi.media.common.IdResolver;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;

import com.metabroadcast.common.collect.OptionalMap;

public interface ContentResolver extends IdResolver<Content> {

    OptionalMap<Alias, Content> resolveAliases(Iterable<Alias> aliases, Publisher source);
    
}
