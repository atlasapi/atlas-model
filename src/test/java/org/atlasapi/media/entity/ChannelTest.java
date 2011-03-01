package org.atlasapi.media.entity;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;


public class ChannelTest {

    @Test
    public void channelShouldInitiate() {
        assertNotNull(Channel.BBC_ONE);
    }
}
