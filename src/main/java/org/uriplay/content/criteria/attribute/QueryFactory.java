package org.uriplay.content.criteria.attribute;

import org.uriplay.content.criteria.AttributeQuery;
import org.uriplay.content.criteria.operator.Operator;

public interface QueryFactory<T> {

	AttributeQuery<T> createQuery(Operator op, Iterable<?> values);
	
	Class<?> requiresOperandOfType();

}
