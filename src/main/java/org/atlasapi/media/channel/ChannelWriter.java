package org.atlasapi.media.channel;

import org.atlasapi.media.channel.Channel;

public interface ChannelWriter {

	Channel createOrUpdate(Channel channel);
	
}
