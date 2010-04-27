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

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.jherd.util.Selection;

import com.google.common.collect.Lists;

public abstract class LogicalOperatorQuery implements ContentQuery {

	private List<ContentQuery> operands = Lists.newArrayList();

	protected Selection selection;
	
	public LogicalOperatorQuery(Collection<ContentQuery> operands) {
		this.operands.addAll(operands);
	}

	public LogicalOperatorQuery() {
	}
	
	public void add(ContentQuery query) {
		operands.add(query);
	}

	public List<ContentQuery> operands() {
		return Collections.unmodifiableList(operands);
	}
	
	public abstract String name();
	
	@Override
	public final String toString() {
		StringBuilder b = new StringBuilder();
		b.append(name() + " (");
		for (ContentQuery operand : operands) {
			b.append(operand.toString()).append(",");
		}
		b.append(")");
		return b.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, excluding("selection"));
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, excluding("selection"));
	}

	private String[] excluding(String fieldName) {
		return new String[] {fieldName};
	}
	
	public void setOperands(List<ContentQuery> operands) {
		this.operands = operands;
	}
	
	@Override
	public ContentQuery withSelection(Selection selection) {
		this.selection = selection;
		return this;
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public abstract LogicalOperatorQuery copyWithOperands(List<ContentQuery> newConjucts);
	
}
