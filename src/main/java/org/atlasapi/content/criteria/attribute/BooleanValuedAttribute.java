package org.atlasapi.content.criteria.attribute;

import org.atlasapi.content.criteria.AttributeQuery;
import org.atlasapi.content.criteria.BooleanAttributeQuery;
import org.atlasapi.content.criteria.operator.BooleanOperator;
import org.atlasapi.content.criteria.operator.Operator;
import org.atlasapi.media.entity.Description;

public class BooleanValuedAttribute extends Attribute<Boolean> {

	BooleanValuedAttribute(String name, Class<? extends Description> target) {
		super(name, target);
	}
	
	BooleanValuedAttribute(String name, Class<? extends Description> target, boolean isCollection) {
		super(name, target, isCollection);
	}

	@Override
	public String toString() {
		return "Boolean valued attribute: " + name;
	}

	@Override
	public AttributeQuery<Boolean> createQuery(Operator op, Iterable<?> values) {
		if (!(op instanceof BooleanOperator)) {
			throw new IllegalArgumentException();
		}
		return new BooleanAttributeQuery(this, (BooleanOperator) op, values);
	}

	@Override
	public Class<?> requiresOperandOfType() {
		return Boolean.class;
	}
}
