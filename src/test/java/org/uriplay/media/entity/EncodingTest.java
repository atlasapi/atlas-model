/* Copyright 2009 British Broadcasting Corporation
   Copyright 2009 Meta Broadcast Ltd

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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import junit.framework.TestCase;

/**
 * Unit test for {@link Encoding}.
 *
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public class EncodingTest extends TestCase {

	Encoding encoding = new Encoding();
	Location location = new Location();
	Item item = new Episode();
	
	public void testMapsMimeTypesToUriplayListForAudioCoding() throws Exception {
		
		encoding.setAudioCoding("audio/mp3");
		assertThat(encoding.getAudioCoding(), is("audio/mpeg"));
	}
	
	public void testMapsMimeTypesToUriplayListForVideoCoding() throws Exception {
		
		encoding.setVideoCoding("video/x-vp6");
		assertThat(encoding.getVideoCoding(), is("video/x-vp6"));
	}
	
}
