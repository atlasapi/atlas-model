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

package org.atlasapi.content.criteria.attribute;

import org.atlasapi.content.criteria.AttributeQuery;
import org.atlasapi.content.criteria.StringAttributeQuery;
import org.atlasapi.content.criteria.operator.Operator;
import org.atlasapi.content.criteria.operator.StringOperator;
import org.atlasapi.media.entity.Identified;

public class StringValuedAttribute extends Attribute<String> {
	
	StringValuedAttribute(String name, Class<? extends Identified> target) {
		super(name, target);
	}
	
	StringValuedAttribute(String name, Class<? extends Identified> target, boolean isCollection) {
		super(name, target, isCollection);
	}
	
	@Override
	public String toString() {
		return "String valued attribute: " + name;
	}

	@Override
	public Class<?> requiresOperandOfType() {
		return String.class;
	}

	@Override
	public AttributeQuery<String> createQuery(Operator op, Iterable<?> values) {
		if (!(op instanceof StringOperator)) {
			throw new IllegalArgumentException();
		}
		return new StringAttributeQuery(this, (StringOperator) op, values);
	}
}
