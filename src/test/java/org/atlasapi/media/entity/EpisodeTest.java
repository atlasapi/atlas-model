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

package org.atlasapi.media.entity;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

/**
 * Unit test for {@link Episode}.
 *
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public class EpisodeTest {

	Episode episode = new Episode();
	Version version = new Version();
	
	@Test
	public void testManagesRelationshipWithVersion() {
		
		episode.addVersion(version);
		assertThat(episode.getVersions(), hasItem(version));
		
		episode.removeVersion(version);
		assertThat(episode.getVersions(), not(hasItem(version)));
	}
	
	@Test
	public void testReturnsFalseForRemoveWhenNoVersionsAdded() throws Exception {
		assertThat(episode.removeVersion(version), is(false));
	}

	@Test
	public void testLocalizedTitles() {
		LocalizedTitle localizedTitle = new LocalizedTitle();
		localizedTitle.setTitle("Alt title");
		localizedTitle.setLocale(new Locale("", "RU"));
		episode.setLocalizedTitles(ImmutableSet.of(localizedTitle));
		String country = localizedTitle.getLocale().getCountry();
		String[] countryCodes = Locale.getISOCountries();

		episode.setId(123L);
		assertTrue(episode.getLocalizedTitles().contains(localizedTitle));
	}
}
