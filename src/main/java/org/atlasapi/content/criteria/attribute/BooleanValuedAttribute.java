package org.atlasapi.content.criteria.attribute;

import org.atlasapi.content.criteria.AttributeQuery;
import org.atlasapi.content.criteria.BooleanAttributeQuery;
import org.atlasapi.content.criteria.operator.EqualsOperator;
import org.atlasapi.content.criteria.operator.Operator;
import org.atlasapi.media.entity.Identified;

public class BooleanValuedAttribute extends Attribute<Boolean> {

	BooleanValuedAttribute(String name, Class<? extends Identified> target) {
		super(name, target);
	}
	
	public BooleanValuedAttribute(String name, Class<? extends Identified> target, boolean isCollection) {
		super(name, target, isCollection);
	}

	@Override
	public String toString() {
		return "Boolean valued attribute: " + name;
	}

	@Override
	public AttributeQuery<Boolean> createQuery(Operator op, Iterable<Boolean> values) {
		if (!(op instanceof EqualsOperator)) {
			throw new IllegalArgumentException();
		}
		return new BooleanAttributeQuery(this, (EqualsOperator) op, values);
	}

	@Override
	public Class<Boolean> requiresOperandOfType() {
		return Boolean.class;
	}
}
