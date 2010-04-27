/* Copyright 2009 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.uriplay.content.criteria.attribute;

import java.util.List;

import org.uriplay.content.criteria.AttributeQuery;
import org.uriplay.content.criteria.EnumAttributeQuery;
import org.uriplay.content.criteria.operator.EnumOperator;
import org.uriplay.content.criteria.operator.Operator;
import org.uriplay.media.entity.Description;

public class EnumValuedAttribute<T extends Enum<T>> extends Attribute<Enum<T>> {
	
	private final Class<T> type;

	EnumValuedAttribute(String name, Class<T> type, Class<? extends Description> target) {
		this(name, type, target, false);
	}
	
	EnumValuedAttribute(String name, Class<T> type, Class<? extends Description> target, boolean isCollection) {
		super(name, target, isCollection);
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "Enum valued attribute: " + name;
	}

	@Override
	public Class<?> requiresOperandOfType() {
		return type;
	}
	
	public T toEnumValue(String value) {
		return Enum.valueOf(type, value);
	}

	@Override
	public AttributeQuery<Enum<T>> createQuery(Operator op, List<?> values) {
		if (!(op instanceof EnumOperator)) {
			throw new IllegalArgumentException();
		}
		return new EnumAttributeQuery<T>(this, (EnumOperator) op, values);
	}
}
