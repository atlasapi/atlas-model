package org.uriplay.content.criteria.operator;

import org.uriplay.content.criteria.operator.Operators.After;
import org.uriplay.content.criteria.operator.Operators.Before;

public interface DateTimeOperatorVisitor<T> {

	T visit(Before before);
	
	T visit(After after);
}
