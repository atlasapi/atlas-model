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

package org.atlasapi.persistence.content.query;

import java.util.List;
import java.util.Set;

import org.atlasapi.content.criteria.AtomicQuery;
import org.atlasapi.content.criteria.AttributeQuery;
import org.atlasapi.content.criteria.ContentQuery;
import org.atlasapi.content.criteria.QueryVisitorAdapter;
import org.atlasapi.content.criteria.attribute.Attribute;
import org.atlasapi.content.criteria.attribute.StringValuedAttribute;

import com.metabroadcast.common.base.Maybe;


public class QueryFragmentExtractor {

	/**
	 * Extracts the part of the query that concern {@link StringValuedAttribute}s passed
	 * Used to extract URI and CURIE query parts.
	 */
	public static Maybe<AttributeQuery<?>> extract(ContentQuery query, final Set<Attribute<?>> attributes) {
		
		 List<Maybe<AttributeQuery<?>>> extracted = query.accept(new QueryVisitorAdapter<Maybe<AttributeQuery<?>>>() {
			
			@Override
			protected Maybe<AttributeQuery<?>> defaultValue(AtomicQuery query) {
				if (query instanceof AttributeQuery<?>) {
					if (attributes.contains(((AttributeQuery<?>) query).getAttribute())) {
						return Maybe.<AttributeQuery<?>>just((AttributeQuery<?>) query);
					} 
				}
				return Maybe.nothing();
			}
		});
		 
		return Maybe.firstElementOrNothing(Maybe.filterValues(extracted));
			
	}
}
