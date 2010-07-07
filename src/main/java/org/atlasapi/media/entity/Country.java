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

package org.atlasapi.media.entity;

import com.google.common.base.Function;

/**
 * Represents a country as an ISO 3166 code 
 */
public class Country {

	private final String code;
	private final String name;

	Country(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	@Override
	public String toString() {
		return code;
	}
	
	public String getName() {
		return name;
	}

	public String code() {
		return code;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Country) {
			return code.equals(((Country) obj).code);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return code.hashCode();
	}

	public static Function<Country, String> UNPACK_COUNTRY_CODE = new Function<Country, String>() {

		@Override
		public String apply(Country country) {
			return country.code;
		}
	};
}
