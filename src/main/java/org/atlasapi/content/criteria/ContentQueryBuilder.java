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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.metabroadcast.applications.client.model.internal.Application;
import org.atlasapi.content.criteria.attribute.QueryFactory;
import org.atlasapi.content.criteria.operator.Operators;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.metabroadcast.common.query.Selection;

public class ContentQueryBuilder {

	private final List<AtomicQuery> queries;
	private Selection selection = Selection.ALL;
	private Optional<Application> application;
	
	public ContentQueryBuilder(Iterable<AtomicQuery> queries) {
		this.queries = Lists.newArrayList(queries);
	}

	public static ContentQueryBuilder query() {
		return new ContentQueryBuilder(Collections.<AtomicQuery>emptyList());
	}
	
	public ContentQuery build() {
		if(application.isPresent()) {
			return new ContentQuery(queries, selection, application.get());
		} else {
			return new ContentQuery(queries, selection);
		}
	}
	
	public ContentQueryBuilder withSelection(Selection selection) {
		this.selection = selection;
		return this;
	}
	
	public ContentQueryBuilder equalTo(QueryFactory<String> attribute, String... values) {
		queries.add(attribute.createQuery(Operators.EQUALS, Arrays.asList(values)));
		return this;
	}
	
	public ContentQueryBuilder equalTo(QueryFactory<String> attribute, Iterable<String> values) {
		queries.add(attribute.createQuery(Operators.EQUALS, values));
		return this;
	}
	
	public ContentQueryBuilder equalTo(QueryFactory<Boolean> attribute, Boolean... values) {
		queries.add(attribute.createQuery(Operators.EQUALS, Arrays.asList(values)));
		return this;
	}
	
	public ContentQueryBuilder anyValue(QueryFactory<Boolean> attribute) {
		return equalTo(attribute, true, false);
	}
	
	public ContentQueryBuilder equalTo(QueryFactory<DateTime> attribute, DateTime... values) {
		queries.add(attribute.createQuery(Operators.EQUALS, Arrays.asList(values)));
		return this;
	}
	
	public <T extends Enum<T>> ContentQueryBuilder equalTo(QueryFactory<Enum<T>> attribute, Enum<T> value) {
		return isAnEnumIn(attribute, ImmutableList.of(value));
	}
	
	public <T extends Enum<T>> ContentQueryBuilder isAnEnumIn(QueryFactory<Enum<T>> attribute, List<Enum<T>> value) {
		queries.add(attribute.createQuery(Operators.EQUALS, ImmutableList.copyOf(value)));
		return this;
	}
	
	public ContentQueryBuilder equalTo(QueryFactory<Integer> attribute, Integer... values) {
		queries.add(attribute.createQuery(Operators.EQUALS, Arrays.asList(values)));
		return this;
	}
	
	public ContentQueryBuilder beginning(QueryFactory<String> attribute, String... values) {
		queries.add(attribute.createQuery(Operators.BEGINNING, Arrays.asList(values)));
		return this;
	}
	
	public ContentQueryBuilder greaterThan(QueryFactory<Integer> attribute, Integer... values) {
		queries.add(attribute.createQuery(Operators.GREATER_THAN, Arrays.asList(values)));
		return this;
	}
	public ContentQueryBuilder lessThan(QueryFactory<Integer> attribute, Integer... values) {
		queries.add(attribute.createQuery(Operators.LESS_THAN, Arrays.asList(values)));
		return this;
	}
		
	public ContentQueryBuilder after(QueryFactory<DateTime> attribute, DateTime... values) {
		queries.add(attribute.createQuery(Operators.AFTER, Arrays.asList(values)));
		return this;
	}
	
	public ContentQueryBuilder before(QueryFactory<DateTime> attribute, DateTime... values) {
		queries.add(attribute.createQuery(Operators.BEFORE, Arrays.asList(values)));
		return this;
	}

	public ContentQueryBuilder withApplication(Application application) {
		this.application = Optional.of(application);
		return this;
	}
}
