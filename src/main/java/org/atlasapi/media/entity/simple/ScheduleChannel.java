package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlType(name="channel", namespace=PLAY_SIMPLE_XML.NS)
public class ScheduleChannel {
	
    private String channelUri;
    private String channelTitle;
    private String channelKey;
    private Channel channel;
    
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

    private List<Item> items = Lists.newArrayList();
    
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
    
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
    
    public Channel getChannel() {
        return channel;
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
    
    public static Function<ScheduleChannel, String> TO_KEY = new Function<ScheduleChannel, String>() {

		@Override
		public String apply(ScheduleChannel input) {
			return input.getChannelKey();
		}
    };

}
