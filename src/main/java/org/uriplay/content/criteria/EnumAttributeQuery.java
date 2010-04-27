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

package org.uriplay.content.criteria;

import java.util.List;

import org.uriplay.content.criteria.attribute.Attribute;
import org.uriplay.content.criteria.operator.EnumOperator;
import org.uriplay.content.criteria.operator.EnumOperatorVisitor;

public class EnumAttributeQuery<T extends Enum<T>> extends AttributeQuery<Enum<T>> {

	private final EnumOperator op;

	public EnumAttributeQuery(Attribute<Enum<T>> attribute, EnumOperator op,  List<?> values) {
		super(attribute, op, values);
		this.op = op;
	}

	public <V> V accept(QueryVisitor<V> v) {
		return v.visit(this);
	}
	
	public <V> V accept(EnumOperatorVisitor<V> v) {
		return op.accept(v);
	}
}
