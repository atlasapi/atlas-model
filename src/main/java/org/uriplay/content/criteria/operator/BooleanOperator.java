package org.uriplay.content.criteria.operator;

public interface BooleanOperator extends Operator {
	
	<V> V accept(BooleanOperatorVisitor<V> v);

}

