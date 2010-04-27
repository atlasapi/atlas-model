package org.uriplay.content.criteria.attribute;

import java.util.List;

import org.uriplay.content.criteria.AttributeQuery;
import org.uriplay.content.criteria.BooleanAttributeQuery;
import org.uriplay.content.criteria.operator.BooleanOperator;
import org.uriplay.content.criteria.operator.Operator;
import org.uriplay.media.entity.Description;

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
	public AttributeQuery<Boolean> createQuery(Operator op, List<?> values) {
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
