package org.atlasapi.media.entity.simple;

import java.util.List;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;

public class ChannelGroupQueryResult implements Iterable<ChannelGroup> {
    
    private List<ChannelGroup> channels = ImmutableList.of();

    public ChannelGroupQueryResult(Iterable<ChannelGroup> channels) {
        this.channels = ImmutableList.copyOf(channels);
    }

    public ChannelGroupQueryResult() {
    }
    
    public List<ChannelGroup> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelGroup> channels) {
        this.channels = ImmutableList.copyOf(channels);
    }

    @Override
    public Iterator<ChannelGroup> iterator() {
        return channels.iterator();
    }

}
