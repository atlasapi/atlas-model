/* Copyright 2009 British Broadcasting Corporation
 
Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.atlasapi.media.reference.entity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import junit.framework.TestCase;

import com.metabroadcast.common.media.MimeType;

/**
 * Unit test for {@link ContainerFormat}.
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public class ContainerFormatTest extends TestCase {

	public void testMapsMimeTypesFromAlternativeNames() throws Exception {
		
		assertThat(ContainerFormat.fromAltName("audio/mp1"), is(MimeType.AUDIO_MPEG));
		assertThat(ContainerFormat.fromAltName("audio/mp3"), is(MimeType.AUDIO_MPEG));
	}
	
	public void testGivesNullForUnknownAlternate() throws Exception {
		
		assertThat(ContainerFormat.fromAltName("fishing/rod"), is(nullValue()));
	}
}
