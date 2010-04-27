package org.uriplay.media.entity;

import org.joda.time.DateTime;

import junit.framework.TestCase;

public class BroadcastTest extends TestCase {
    public void testEqualBroadcasts() throws Exception {
        Broadcast broadcast1 = new Broadcast();
        Broadcast broadcast2 = new Broadcast();
        DateTime time = new DateTime();
        
        assertEquals(broadcast1, broadcast2);
        
        broadcast1.setCanonicalUri("uri");
        broadcast2.setCanonicalUri("uri");
        
        assertEquals(broadcast1, broadcast2);
        
        broadcast1.setCanonicalUri(null);
        broadcast2.setCanonicalUri(null);
        
        broadcast1.setBroadcastOn("on");
        broadcast2.setBroadcastOn("on");
        
        assertEquals(broadcast1, broadcast2);
        
        broadcast1.setBroadcastOn(null);
        broadcast2.setBroadcastOn(null);
        
        broadcast1.setTransmissionTime(time);
        broadcast2.setTransmissionTime(time);
        
        assertEquals(broadcast1, broadcast2);
        
        broadcast1.setBroadcastOn("on");
        broadcast2.setBroadcastOn("on");
        
        assertEquals(broadcast1, broadcast2);
    }
    
    public void testUnequalBroadcasts() throws Exception {
        Broadcast broadcast1 = new Broadcast();
        Broadcast broadcast2 = new Broadcast();
        DateTime time = new DateTime();
        
        broadcast1.setCanonicalUri("uri1");
        
        assertNotSame(broadcast1, broadcast2);
        
        broadcast2.setCanonicalUri("uri");
        
        assertNotSame(broadcast1, broadcast2);
        
        broadcast1.setCanonicalUri(null);
        broadcast2.setCanonicalUri(null);
        
        broadcast1.setBroadcastOn("o");
        broadcast2.setBroadcastOn("on");
        
        assertNotSame(broadcast1, broadcast2);
        
        broadcast1.setBroadcastOn(null);
        broadcast2.setBroadcastOn(null);
        
        broadcast1.setTransmissionTime(time);
        broadcast2.setTransmissionTime(new DateTime());
        
        assertNotSame(broadcast1, broadcast2);
        
        broadcast1.setBroadcastOn("on");
        broadcast2.setBroadcastOn("on");
        
        assertNotSame(broadcast1, broadcast2);
    }
}
