package org.atlasapi.media.topic;

import org.atlasapi.media.common.IdResolver;
import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.Publisher;

import com.metabroadcast.common.collect.OptionalMap;

public interface TopicResolver extends IdResolver<Topic> {

    OptionalMap<Alias, Topic> resolveAliases(Iterable<Alias> aliases, Publisher source);
    
}
