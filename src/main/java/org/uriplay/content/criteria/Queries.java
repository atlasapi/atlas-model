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

import java.util.Arrays;
import java.util.Collection;

import org.joda.time.DateTime;
import org.uriplay.content.criteria.attribute.QueryFactory;
import org.uriplay.content.criteria.operator.Operators;

import com.google.common.collect.Lists;

public class Queries {

	public static ContentQuery equalTo(QueryFactory<String> attribute, String... values) {
		return attribute.createQuery(Operators.EQUALS, Arrays.asList(values));
	}
	
	public static ContentQuery equalTo(QueryFactory<String> attribute, Collection<String> values) {
		return attribute.createQuery(Operators.EQUALS, Lists.newArrayList(values));
	}
	
	public static ContentQuery searchFor(QueryFactory<String> attribute, String... values) {
		return attribute.createQuery(Operators.SEARCH, Arrays.asList(values));
	}
	
	public static ContentQuery equalTo(QueryFactory<Boolean> attribute, Boolean... values) {
		return attribute.createQuery(Operators.EQUALS, Arrays.asList(values));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Enum<T>> ContentQuery equalTo(QueryFactory<Enum<T>> attribute, Enum<T> value) {
		return attribute.createQuery(Operators.EQUALS, Arrays.asList(value));
	}
	
	public static <T extends Enum<T>> ContentQuery equalTo(QueryFactory<Enum<T>> attribute, Enum<T>... values) {
		return attribute.createQuery(Operators.EQUALS, Arrays.asList(values));
	}
	
	public static ContentQuery equalTo(QueryFactory<Integer> attribute, Integer... values) {
		return attribute.createQuery(Operators.EQUALS, Arrays.asList(values));
	}
	
	public static ContentQuery beginning(QueryFactory<String> attribute, String... values) {
		return attribute.createQuery(Operators.BEGINNING, Arrays.asList(values));
	}
	
	public static ContentQuery greaterThan(QueryFactory<Integer> attribute, Integer... values) {
		return attribute.createQuery(Operators.GREATER_THAN, Arrays.asList(values));
	}
	
	public static ContentQuery lessThan(QueryFactory<Integer> attribute, Integer... values) {
		return attribute.createQuery(Operators.LESS_THAN, Arrays.asList(values));
	}
	
	public static ContentQuery after(QueryFactory<DateTime> attribute, DateTime... values) {
		return attribute.createQuery(Operators.AFTER, Arrays.asList(values));
	}
	
	public static ContentQuery before(QueryFactory<DateTime> attribute, DateTime... values) {
		return attribute.createQuery(Operators.BEFORE, Arrays.asList(values));
	}

	public static ContentQuery and(ContentQuery... conjuncts) {
		return new ConjunctiveQuery(Arrays.asList(conjuncts));
	}
}
