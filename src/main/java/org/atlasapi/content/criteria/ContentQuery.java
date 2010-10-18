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

package org.atlasapi.content.criteria;

import java.util.List;
import java.util.Map;

import org.atlasapi.application.ApplicationConfiguration;
import org.atlasapi.content.criteria.attribute.Attribute;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.metabroadcast.common.query.Selection;

public class ContentQuery {

	public static final ContentQuery MATCHES_EVERYTHING = new ContentQuery(ImmutableList.<AtomicQuery>of(), Selection.ALL);

	private ImmutableSet<AtomicQuery> operands;
	
	/*
	 * Added by ApplicationConfigurationQueryExecutor to describe restrictions which don't apply if the restricted attribute is not set.
	 * e.g. versions = [] || ∃version s.t. constraint holds.
	 */
	private ImmutableSet<AtomicQuery> softConstraints;

	private final Selection selection;

	private final ApplicationConfiguration configuration;
	
	public ContentQuery(AtomicQuery operand) {
		this(ImmutableList.of(operand), Selection.ALL);
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands) {
		this(operands, Selection.ALL, ApplicationConfiguration.DEFAULT_CONFIGURATION);
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands, Selection selection) {
		this(operands, selection, ApplicationConfiguration.DEFAULT_CONFIGURATION);
	}

	public ContentQuery(Iterable<AtomicQuery> operands, Selection selection, ApplicationConfiguration configuration) {
		this.operands = ImmutableSet.copyOf(operands);
		this.selection = selection;
		this.configuration = configuration;
		this.softConstraints = ImmutableSet.of();
	}
	
	public ImmutableSet<AtomicQuery> operands() {
		return operands;
	}
	
	@Override
	public final String toString() {
		StringBuilder b = new StringBuilder();
		b.append("query=");
		for (AtomicQuery operand : operands) {
			b.append(operand.toString()).append(" and ");
		}
		return b.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this instanceof ContentQuery) {
			ContentQuery other = (ContentQuery) obj;
			return this.operands.equals(other.operands) && this.selection.equals(other.selection);
		}
		return false;
	}

	@Override
	public int hashCode() {
	    return operands.hashCode();
	}
	
	public Selection getSelection() {
		return selection;
	}
	
	public ApplicationConfiguration getConfiguration() {
		return configuration;
	}

	public ContentQuery copyWithOperands(Iterable<AtomicQuery> newConjucts) {
		ContentQuery conjunctiveQuery = new ContentQuery(newConjucts, selection);
		return conjunctiveQuery;
	}

	public static ContentQuery joinTo(ContentQuery original, ContentQuery toAdd) {
		List<AtomicQuery> allConjucts = Lists.newArrayList(original.operands());
		allConjucts.addAll(toAdd.operands());
		ContentQuery contentQuery = new ContentQuery(allConjucts, original.getSelection(), original.getConfiguration());
		contentQuery.setSoftConstraints(Iterables.concat(original.getSoftConstraints(),toAdd.getSoftConstraints()));
		return contentQuery;
	}
	
	public <V> List<V> accept(QueryVisitor<V> v) {
		List<V> result = Lists.newArrayListWithCapacity(operands.size());
		for (AtomicQuery operand : operands) {
			result.add(operand.accept(v));
		}
		return result;
	}

	public ContentQuery copyWithSelection(Selection newSelection) {
		ContentQuery contentQuery = new ContentQuery(operands, newSelection, getConfiguration());
		contentQuery.setSoftConstraints(getSoftConstraints());
		return contentQuery;
	}
	
	public ContentQuery copyWithApplicationConfiguration(ApplicationConfiguration configuration){
		ContentQuery contentQuery = new ContentQuery(operands, getSelection(), configuration);
		contentQuery.setSoftConstraints(getSoftConstraints());
		return contentQuery;
	}
	
	public ContentQuery copyWithSoftConstraintsApplied() {
		ContentQuery contentQuery = new ContentQuery(Iterables.concat(operands(),getSoftConstraints()), getSelection(), configuration);
		return contentQuery;
	}
	
	public void setSoftConstraints(Iterable<AtomicQuery> softConstraints) {
		this.softConstraints = ImmutableSet.copyOf(softConstraints);
	}

	public ImmutableSet<AtomicQuery> getSoftConstraints() {
		return softConstraints;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Attribute<?>,List<?>> operandMap() {
		final Map<Attribute<?>,List<?>> operandMap = Maps.newHashMap();
		for (AtomicQuery atom : operands()) {
			atom.accept(new QueryVisitor(){

				@Override
				public Object visit(IntegerAttributeQuery query) {
					operandMap.put(query.getAttribute(), query.getValue());
					return null;
				}

				@Override
				public Object visit(StringAttributeQuery query) {
					operandMap.put(query.getAttribute(), query.getValue());
					return null;
				}

				@Override
				public Object visit(BooleanAttributeQuery query) {
					operandMap.put(query.getAttribute(), query.getValue());
					return null;
				}

				@Override
				public Object visit(EnumAttributeQuery query) {
					operandMap.put(query.getAttribute(), query.getValue());
					return null;
				}

				@Override
				public Object visit(DateTimeAttributeQuery dateTimeAttributeQuery) {
					operandMap.put(dateTimeAttributeQuery.getAttribute(), dateTimeAttributeQuery.getValue());
					return null;
				}

				@Override
				public Object visit(MatchesNothing noOp) {
					return null;
				}});
		}
		return operandMap;
	}
}
