package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlType(name="channel", namespace=PLAY_SIMPLE_XML.NS)
public class ScheduleChannel {
	
    private Channel channel;
    private List<Item> content = Lists.newArrayList();
    
    public List<Item> getItems() {
        return content;
    }
    
    public void setItems(List<Item> items) {
        this.content = items;
    }
    
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    
    public Channel getChannel() {
        return channel;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ScheduleChannel) {
            ScheduleChannel other = (ScheduleChannel) that;
            return Objects.equal(channel, other.channel) 
                && Objects.equal(content, other.content);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(channel, content);
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(ScheduleChannel.class)
            .add("channel", channel)
            .add("content", content)
            .toString();
    }

}
