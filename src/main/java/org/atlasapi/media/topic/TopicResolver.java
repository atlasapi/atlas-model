package org.atlasapi.media.topic;

import org.atlasapi.media.common.Id;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.util.Resolved;

import com.metabroadcast.common.collect.OptionalMap;

public interface TopicResolver {

    Resolved<Topic> resolveIds(Iterable<Id> ids);
    
    OptionalMap<String, Topic> resolveAliases(Iterable<String> aliases, Publisher source);
    
}
