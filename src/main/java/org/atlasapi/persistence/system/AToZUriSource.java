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

package org.atlasapi.persistence.system;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

public class AToZUriSource implements Iterable<String> {

	private final List<String> uris;
	private final String prefix;
	private final String suffix;
	
	private boolean includeZeroDashNine = false;

	public AToZUriSource(String prefix, String suffix, boolean includeZeroDashNine) {
		this.prefix = prefix;
		this.suffix = suffix;
		this.includeZeroDashNine = includeZeroDashNine;
		this.uris = build();
	}

	private List<String> build() {
		List<String> modifiableUris = Lists.newArrayListWithCapacity(27);
		for(char c = 'z'; c >= 'a'; c--) {
			modifiableUris.add(prefix + c + suffix);
		}
		if (includeZeroDashNine) {
			modifiableUris.add(prefix + "0-9" + suffix);
		}
		return Collections.unmodifiableList(modifiableUris);
	}
	
	public Iterator<String> iterator() {
		return Collections.unmodifiableList(uris).iterator();
	}

	public void setIncludeZeroNine(boolean includeZeroDashNine) {
		this.includeZeroDashNine = includeZeroDashNine;
	}
}
