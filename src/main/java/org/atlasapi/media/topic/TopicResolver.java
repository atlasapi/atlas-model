package org.atlasapi.media.topic;

import org.atlasapi.media.common.IdResolver;
import org.atlasapi.media.entity.Publisher;

import com.metabroadcast.common.collect.OptionalMap;

public interface TopicResolver extends IdResolver<Topic> {

    OptionalMap<String, Topic> resolveAliases(Iterable<String> aliases, Publisher source);
    
}
