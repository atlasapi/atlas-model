package org.atlasapi.media.channel;

import com.google.common.base.Optional;

public interface ChannelGroupStore {

    Optional<ChannelGroup> channelGroupFor(Long id);
    
    Iterable<ChannelGroup> channelGroupsFor(Iterable<Long> ids);
    
    Iterable<ChannelGroup> channelGroups();
    
    Iterable<ChannelGroup> channelGroupsFor(Channel channel);
    
    ChannelGroup store(ChannelGroup group);
    
}
