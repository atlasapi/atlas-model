package org.atlasapi.media.channel;

import org.atlasapi.media.common.Id;

import com.google.common.base.Optional;

public interface ChannelGroupResolver {
    
     Optional<ChannelGroup> fromAlias(String alias);
     
     Optional<ChannelGroup> channelGroupFor(Id id);
     
     Iterable<ChannelGroup> channelGroupsFor(Iterable<Id> ids);
     
     Iterable<ChannelGroup> channelGroups();
     
     Iterable<ChannelGroup> channelGroupsFor(Channel channel);
}
