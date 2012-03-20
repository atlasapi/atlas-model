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

public class AToBUriSource implements Iterable<String> {

	private final List<String> uris = Lists.newArrayListWithCapacity(26);
	
	public AToBUriSource(String prefix, String suffix) {
		for(char c = 'A'; c <= 'B'; c++) {
			uris.add(prefix + c + suffix);
		}
	}
	
	public Iterator<String> iterator() {
		return Collections.unmodifiableList(uris).iterator();
	}
}
