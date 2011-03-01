package org.atlasapi.media.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class ChannelTest {

    @Test
    public void channelShouldInitiate() {
        assertNotNull(Channel.BBC_ONE);
    }
    
    
    @Test
    public void shouldCreateChannel() {
        String uri = "http://www.something.com/channel/somechannel";
        assertEquals(new Channel("somechannel", uri, "somechannel"), Channel.fromUri(uri).requireValue());
    }
}
