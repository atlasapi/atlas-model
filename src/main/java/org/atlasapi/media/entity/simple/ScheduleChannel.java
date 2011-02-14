package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;

@XmlType(name="channel", namespace=PLAY_SIMPLE_XML.NS)
public class ScheduleChannel {
    private String channelUri;
    private String channelTitle;
    private String channelKey;
    
    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelKey() {
        return channelKey;
    }

    public void setChannelKey(String channelKey) {
        this.channelKey = channelKey;
    }

    private List<Item> items;
    
    public String getChannelUri() {
        return channelUri;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setChannelUri(String channelUri) {
        this.channelUri = channelUri;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScheduleChannel) {
            ScheduleChannel scheduleChannel = (ScheduleChannel) obj;
            return channelUri.equals(scheduleChannel.channelUri) && items.equals(scheduleChannel.items);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return channelUri.hashCode();
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(ScheduleChannel.class).addValue(channelUri).addValue(items).toString();
    }
}
