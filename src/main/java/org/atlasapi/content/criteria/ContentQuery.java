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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.application.v3.DefaultApplication;
import org.atlasapi.content.criteria.attribute.Attributes;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.metabroadcast.common.query.Selection;
import java.util.Set;
import org.atlasapi.output.Annotation;

public class ContentQuery {

    private ImmutableSet<Annotation> annotations;
    
	private ImmutableSet<AtomicQuery> operands;
	
	/*
	 * Added by ApplicationConfigurationQueryExecutor to describe restrictions which don't apply if the restricted attribute is not set.
	 * e.g. versions = [] || ∃version s.t. constraint holds.
	 */
	private ImmutableSet<AtomicQuery> softConstraints;

	private final Selection selection;

	private final Application application;
    	
	public ContentQuery(AtomicQuery operand) {
		this(ImmutableList.of(operand), Selection.ALL);
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands) {
		this(operands, Selection.ALL, DefaultApplication.createDefault());
	}
	
	public ContentQuery(Iterable<AtomicQuery> operands, Selection selection) {
		this(operands, selection, DefaultApplication.createDefault());
	}
    
    public ContentQuery(Iterable<AtomicQuery> operands, Selection selection, Application application) {
		this(operands, Annotation.defaultAnnotations(), selection, application);
	}

	public ContentQuery(Iterable<AtomicQuery> operands, Set<Annotation> annotations, Selection selection, Application application) {
		this.operands = ImmutableSet.copyOf(operands);
        this.annotations = ImmutableSet.copyOf(annotations);
		this.selection = selection;
		this.application = application;
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
	
	public Application getApplication() {
		return application;
	}

    public ImmutableSet<Annotation> getAnnotations() {
        return annotations;
    }
    
    public ContentQuery copyWithAnnotations(Set<Annotation> annotations) {
		ContentQuery newQuery = new ContentQuery(operands, annotations, selection, application);
        newQuery.setSoftConstraints(softConstraints);
		return newQuery;
	}

	public ContentQuery copyWithOperands(Iterable<AtomicQuery> newConjucts) {
		ContentQuery conjunctiveQuery = new ContentQuery(newConjucts, selection, application);
		conjunctiveQuery.setSoftConstraints(softConstraints);
		return conjunctiveQuery;
	}

	public static ContentQuery joinTo(ContentQuery original, ContentQuery toAdd) {
		List<AtomicQuery> allConjucts = Lists.newArrayList(original.operands());
		allConjucts.addAll(toAdd.operands());
		ImmutableSet.Builder<Annotation> annotations = new ImmutableSet.Builder();
		annotations.addAll(original.getAnnotations());
		annotations.addAll(toAdd.getAnnotations());
		ContentQuery contentQuery = new ContentQuery(allConjucts, annotations.build(), original.getSelection(), original.getApplication());
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
		ContentQuery contentQuery = new ContentQuery(operands, newSelection, getApplication());
		contentQuery.setSoftConstraints(getSoftConstraints());
		return contentQuery;
	}
	
	public ContentQuery copyWithApplication(Application application){
		ContentQuery contentQuery = new ContentQuery(operands, getAnnotations(), getSelection(), application);
		contentQuery.setSoftConstraints(getSoftConstraints());
		return contentQuery;
	}
	
	public ContentQuery copyWithSoftConstraintsApplied() {
		return new ContentQuery(Iterables.concat(operands(),getSoftConstraints()), getSelection(), application);
	}
	
	public void setSoftConstraints(Iterable<AtomicQuery> softConstraints) {
		this.softConstraints = ImmutableSet.copyOf(softConstraints);
	}

	public ImmutableSet<AtomicQuery> getSoftConstraints() {
		return softConstraints;
	}
	
	public ImmutableSet<Publisher> includedPublishers() {
		
		List<List<Publisher>> publishers = accept(new QueryVisitorAdapter<List<Publisher>>() {

			@Override
			@SuppressWarnings("unchecked")
			public List<Publisher> visit(EnumAttributeQuery<?> query) {
				if (!Attributes.DESCRIPTION_PUBLISHER.equals(query.getAttribute())) {
					return defaultValue(query);
				}
				return (List<Publisher>) query.getValue();
			}

			protected List<Publisher> defaultValue(AtomicQuery query) {
				return ImmutableList.of();
			}
		});

		return ImmutableSet.copyOf(Iterables.concat(publishers));
	}

    public boolean allowsSource(Publisher publisher) {
        return application.getConfiguration().isReadEnabled(publisher);
    }
}
