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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jherd.util.Selection;
import org.uriplay.content.criteria.attribute.Attribute;
import org.uriplay.content.criteria.operator.Operator;

public abstract class AttributeQuery<T> implements ContentQuery {

	private Attribute<T> attribute;
	private List<?> values;
	private final Operator op;
	
	private Selection selection;
	
	public AttributeQuery(Attribute<T> attribute, Operator op,  List<?> values) {
		this.op = op;
		for (Object value : values) {
			Class<?> lhs = attribute.requiresOperandOfType();
			Class<?> rhs = value.getClass();
			if (!op.canBeAppliedTo(lhs, rhs)) {
				throw new IllegalArgumentException("Wrong types for operator, lhs " + lhs.getSimpleName() + " found " + rhs.getSimpleName());
			}
		}
		this.attribute = attribute;
		this.values = values;
	}

	public Attribute<T> getAttribute() {
		return attribute;
	}
	
	public void setAttribute(Attribute<T> attribute) {
		this.attribute = attribute;
	}
	
	public String getAttributeName() {
		return attribute.externalName();
	}
	
	public List<?> getValue() {
		return values;
	}
	
	public void setValue(List<Object> value) {
		this.values = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public String toString() {
		return "(" + getAttributeName() + " " + op.toString() + " " + values  + ")";
	}
	
	public Operator getOperator() {
		return op;
	}
	
	@Override
	public ContentQuery withSelection(Selection selection) {
		this.selection = selection;
		return this;
	}
	
	@Override
	public Selection getSelection() {
		return selection;
	}
}
