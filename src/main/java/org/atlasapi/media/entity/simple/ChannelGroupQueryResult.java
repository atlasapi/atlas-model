package org.atlasapi.media.entity.simple;

import java.util.List;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;

public class ChannelGroupQueryResult implements Iterable<ChannelGroup> {
    
    private List<ChannelGroup> channelGroups = ImmutableList.of();

    public ChannelGroupQueryResult(Iterable<ChannelGroup> channelGroups) {
        this.channelGroups = ImmutableList.copyOf(channelGroups);
    }

    public ChannelGroupQueryResult() {
    }
    
    public List<ChannelGroup> getChannelGroups() {
        return channelGroups;
    }

    public void setChannelGroups(List<ChannelGroup> channelGroups) {
        this.channelGroups = ImmutableList.copyOf(channelGroups);
    }

    @Override
    public Iterator<ChannelGroup> iterator() {
        return channelGroups.iterator();
    }

}
