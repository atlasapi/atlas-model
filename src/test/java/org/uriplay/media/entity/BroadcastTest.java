/* Copyright 2010 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

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
