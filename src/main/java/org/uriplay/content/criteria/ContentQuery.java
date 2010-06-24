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

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.metabroadcast.common.query.Selection;

public class ContentQuery {

	public static final ContentQuery MATCHES_EVERYTHING = new ContentQuery(ImmutableList.<AtomicQuery>of(), Selection.ALL);

	private ImmutableList<AtomicQuery> operands;

	private final Selection selection;
	
	public ContentQuery(AtomicQuery operand) {
		this(ImmutableList.of(operand), null);
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands) {
		this(operands, null);
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands, Selection selection) {
		this.selection = selection;
		this.operands = ImmutableList.copyOf(operands);
	}

	public List<AtomicQuery> operands() {
		return Collections.unmodifiableList(operands);
	}
	
	@Override
	public final String toString() {
		StringBuilder b = new StringBuilder();
		b.append(name() + " (");
		for (AtomicQuery operand : operands) {
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
	
	public Selection getSelection() {
		return selection;
	}

	public String name() {
		return "AND";
	}
	
	public ContentQuery copyWithOperands(Iterable<AtomicQuery> newConjucts) {
		ContentQuery conjunctiveQuery = new ContentQuery(newConjucts, selection);
		return conjunctiveQuery;
	}

	public static ContentQuery joinTo(ContentQuery original, ContentQuery toAdd) {
		List<AtomicQuery> allConjucts = Lists.newArrayList(original.operands());
		allConjucts.addAll(toAdd.operands());
		return new ContentQuery(allConjucts, original.getSelection());
	}
	
	public <V> List<V> accept(QueryVisitor<V> v) {
		List<V> result = Lists.newArrayListWithCapacity(operands.size());
		for (AtomicQuery operand : operands) {
			result.add(operand.accept(v));
		}
		return result;
	}

	public ContentQuery copyWithSelection(Selection newSelection) {
		return new ContentQuery(operands, newSelection);
	}
}
