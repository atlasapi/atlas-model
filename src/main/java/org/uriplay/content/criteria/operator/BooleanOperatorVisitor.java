package org.uriplay.content.criteria.operator;

import org.uriplay.content.criteria.operator.Operators.Equals;

public interface BooleanOperatorVisitor<V> {
	
	V visit(Equals equals);

}
