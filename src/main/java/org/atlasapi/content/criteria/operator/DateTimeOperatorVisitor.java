package org.atlasapi.content.criteria.operator;

import org.atlasapi.content.criteria.operator.Operators.After;
import org.atlasapi.content.criteria.operator.Operators.Before;

public interface DateTimeOperatorVisitor<T> {

	T visit(Before before);
	
	T visit(After after);
}
