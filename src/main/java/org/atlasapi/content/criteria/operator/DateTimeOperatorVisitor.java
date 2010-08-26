package org.atlasapi.content.criteria.operator;

import org.atlasapi.content.criteria.operator.Operators.After;
import org.atlasapi.content.criteria.operator.Operators.Before;
import org.atlasapi.content.criteria.operator.Operators.Equals;

public interface DateTimeOperatorVisitor<T> {

	T visit(Before before);
	
	T visit(After after);

	T visit(Equals equals);
}
