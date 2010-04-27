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

import static org.uriplay.content.criteria.MatchesNothing.isEquivalentTo;
import static org.uriplay.content.criteria.Queries.*;
import static org.uriplay.content.criteria.attribute.Attributes.*;

import org.jmock.integration.junit3.MockObjectTestCase;

public class MatchesNothingTest extends MockObjectTestCase {

	public void test() throws Exception {
		
		assertTrue(isEquivalentTo(MatchesNothing.get()));
		assertFalse(isEquivalentTo(equalTo(BRAND_TITLE, "test")));
		assertTrue(isEquivalentTo(and(MatchesNothing.get(), equalTo(BRAND_TITLE, "test"))));
	}
}
