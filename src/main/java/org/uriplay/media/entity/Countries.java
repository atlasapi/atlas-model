/* Copyright 2010 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

package org.uriplay.media.entity;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Splitter;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;

public class Countries {

	public static Country GB = new Country("GB", "United Kingdom");
	public static Country IE = new Country("IE", "Ireland");
	public static Country US = new Country("US", "United States");

	private static Multimap<Country, String> aliases = aliases();
	private static Map<String, Country> directory = buildDirectory();

	public static Country fromCode(String code) {
		return directory.get(code.toUpperCase());
	}
	
	private static final Pattern DELIMTER_PATTERN = Pattern.compile("\\s|;|,");
	
	public static Set<Country> fromDelimtedList(String list) {
		return fromCodes(Splitter.on(DELIMTER_PATTERN).split(list));
	}
	
	private static Multimap<Country, String> aliases() {
		Multimap<Country, String> map = HashMultimap.create();
		map.put(GB, "UK");
		return map;
	}
	
	private static Map<String, Country> buildDirectory() {
		Map<String, Country> dir = Maps.newHashMap();
		Set<Country> countries = Sets.newHashSet(GB, IE);
		for (Country country : countries) {
			dir.put(country.code(), country);
			for (String alias : aliases.get(country)) {
				dir.put(alias, country);
			}
		}
		return dir;
	}

	public static Set<String> toCodes(Collection<Country> availableCountries) {
		return Sets.newHashSet(Collections2.transform(availableCountries, Country.UNPACK_COUNTRY_CODE));
	}

	public static Set<Country> fromCodes(Iterable<String> countryCodes) {
		Set<Country> countries = Sets.newHashSet();
		for (String code : countryCodes) {
			Country country = fromCode(code);
			if (country != null) {
				countries.add(country);
			}
		}
		return countries;
	}
}
