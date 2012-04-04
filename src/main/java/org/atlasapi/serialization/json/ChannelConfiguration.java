package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.channel.Channel;

/**
 */
@JsonDeserialize(builder=Channel.Builder.class)
public interface ChannelConfiguration {
    
}
