package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableList;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="channel_groups")
@XmlType(name="channel_groups", namespace=PLAY_SIMPLE_XML.NS)
public class ChannelGroupQueryResult {
    
    private List<ChannelGroup> channelGroups = ImmutableList.of();

    public ChannelGroupQueryResult(Iterable<ChannelGroup> channelGroups) {
        this.channelGroups = ImmutableList.copyOf(channelGroups);
    }

    public ChannelGroupQueryResult() {
    }

    @XmlElements(@XmlElement(name = "channel_group", type=ChannelGroup.class, namespace=PLAY_SIMPLE_XML.NS))
    public List<ChannelGroup> getChannelGroups() {
        return channelGroups;
    }

    public void setChannelGroups(Iterable<ChannelGroup> channelGroups) {
        this.channelGroups = ImmutableList.copyOf(channelGroups);
    }
}
