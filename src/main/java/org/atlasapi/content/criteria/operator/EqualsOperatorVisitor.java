package org.atlasapi.content.criteria.operator;

import org.atlasapi.content.criteria.operator.Operators.Equals;

public interface EqualsOperatorVisitor<V> {

    V visit(Equals equals);

}
