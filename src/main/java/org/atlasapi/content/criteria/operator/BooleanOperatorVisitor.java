package org.atlasapi.content.criteria.operator;

import org.atlasapi.content.criteria.operator.Operators.Equals;

public interface BooleanOperatorVisitor<V> {
	
	V visit(Equals equals);

}
