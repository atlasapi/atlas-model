package org.atlasapi.media.entity.simple;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class ChannelQueryResult {

    private List<Channel> channels = ImmutableList.of();

    public ChannelQueryResult(Iterable<Channel> channels) {
        this.channels = ImmutableList.copyOf(channels);
    }

    public ChannelQueryResult() {
    }
    
    public List<Channel> getChannels() {
        return channels;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = ImmutableList.copyOf(channels);
    }

}
