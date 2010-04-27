package org.uriplay.content.criteria.attribute;

import java.util.List;

import org.uriplay.content.criteria.ContentQuery;
import org.uriplay.content.criteria.operator.Operator;

public interface QueryFactory<T> {

	ContentQuery createQuery(Operator op, List<?> values);
	
	Class<?> requiresOperandOfType();

}
