package org.atlasapi.content.criteria.attribute;

import org.atlasapi.content.criteria.AttributeQuery;
import org.atlasapi.content.criteria.operator.Operator;

public interface QueryFactory<T> {

	AttributeQuery<T> createQuery(Operator op, Iterable<T> values);
	
	Class<T> requiresOperandOfType();

}
