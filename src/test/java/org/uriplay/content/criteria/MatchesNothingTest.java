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

package org.uriplay.content.criteria;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.uriplay.content.criteria.ContentQueryBuilder.query;
import static org.uriplay.content.criteria.MatchesNothing.isEquivalentTo;
import static org.uriplay.content.criteria.attribute.Attributes.BRAND_TITLE;

import java.util.Collections;

import org.junit.Test;
import org.uriplay.content.criteria.operator.Operators;

import com.google.common.collect.ImmutableList;

public class MatchesNothingTest {

	@Test
	public void test() throws Exception {
		
		assertTrue(isEquivalentTo(MatchesNothing.asQuery()));
		assertFalse(isEquivalentTo(query().equalTo(BRAND_TITLE, "test").build()));
		assertTrue(isEquivalentTo(new ContentQuery(ImmutableList.of(MatchesNothing.get(), BRAND_TITLE.createQuery(Operators.EQUALS, Collections.singletonList("test"))))));
	}
}
