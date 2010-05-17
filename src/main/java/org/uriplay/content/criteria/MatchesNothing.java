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

import com.metabroadcast.common.query.Selection;

/**
 * I represent a query that is never satisfied by any 
 * content.  Intermediate query processors may replace satisfiable 
 * queries (or subqueries) with me to indicate that they have determined
 * that no content matches a query.
 *   
 * @author John Ayres (john@metabroadcast.com)
 */
public class MatchesNothing implements ContentQuery {

	private Selection selection;
	
	private static MatchesNothing INSTANCE = new MatchesNothing();
	
	public static MatchesNothing get() {
		return INSTANCE;
	}
	
	private MatchesNothing() { /* SINGLETON */ }

	public <V> V accept(QueryVisitor<V> visitor) {
		return visitor.visit(this);
	}

	public Selection getSelection() {
		return selection;
	}

	public ContentQuery withSelection(Selection selection) {
		this.selection = selection;
		return this;
	}
	
	@Deprecated
    @Override
    public ContentQuery withSelection(org.jherd.util.Selection selection) {
	    if (selection != null) {
            this.selection = new Selection(selection.getStartIndex(), selection.getLimit());
	    }
        return this;
    }

	public static boolean isEquivalentTo(ContentQuery query) {
		
		return query.accept(new QueryVisitorAdapter<Boolean>() {
			
			@Override
			public Boolean visit(ConjunctiveQuery conjunctiveQuery) {
				for (ContentQuery subquery : conjunctiveQuery.operands()) {
					if (isEquivalentTo(subquery)) {
						return true;
					}
				}
				return false;
			}
			
			@Override
			public Boolean visit(MatchesNothing nothing) {
				return true;
			}
			
			@Override
			protected Boolean defaultValue(ContentQuery query) {
				return false;
			}
		});
	}
}
