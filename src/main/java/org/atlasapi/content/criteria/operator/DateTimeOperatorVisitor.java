package org.atlasapi.content.criteria.operator;

import org.atlasapi.content.criteria.operator.Operators.After;
import org.atlasapi.content.criteria.operator.Operators.Before;

public interface DateTimeOperatorVisitor<V> extends EqualsOperatorVisitor<V> {

    V visit(Before before);

    V visit(After after);

}
