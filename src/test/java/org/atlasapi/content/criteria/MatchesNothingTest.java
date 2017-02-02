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

package org.atlasapi.content.criteria;

import static org.atlasapi.content.criteria.ContentQueryBuilder.query;
import static org.atlasapi.content.criteria.MatchesNothing.isEquivalentTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import com.metabroadcast.applications.client.model.internal.Application;
import com.metabroadcast.common.query.Selection;
import org.atlasapi.content.criteria.attribute.Attributes;
import org.junit.Test;

import java.util.Collections;

public class MatchesNothingTest {

	@Test
	public void test() throws Exception {
		
		assertTrue(isEquivalentTo(
				new ContentQuery(
					Collections.singleton(MatchesNothing.get()),
					Selection.ALL,
					mock(Application.class)
				)
		));

		assertFalse(isEquivalentTo(
				query()
						.equalTo(Attributes.DESCRIPTION_GENRE, "test")
						.withApplication(mock(Application.class))
						.build()
				)
		);
	}
}
