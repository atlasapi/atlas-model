/* Copyright 2009 British Broadcasting Corporation
   Copyright 2009 Meta Broadcast Ltd

Licensed under the Apache License, Version 2.0 (the "License"); you
may not use this file except in compliance with the License. You may
obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
implied. See the License for the specific language governing
permissions and limitations under the License. */

/**
 * 
 */
package org.atlasapi.media.util;

import java.util.Collection;
import java.util.Set;

import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.Playlist;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Predicate that determines whether a given bean is a child of at least
 * one node in a given graph. The implementation is currently specific to
 * {@link Playlist}s so does not work for general object graphs.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
public class ChildFinder implements Predicate<Object> {
	
	private final Set<Object> notToBeConsideredRoot = Sets.newHashSet();

	public ChildFinder(Collection<?> beans) {
		
		if (beans != null) {
		
			for (Object bean : beans) {
				
				if (bean instanceof Playlist) {
					Collection<Item> items = ((Playlist) bean).getItems();
					notToBeConsideredRoot.addAll(items);
					Collection<Playlist> lists = ((Playlist) bean).getPlaylists();
					notToBeConsideredRoot.addAll(lists);
				} else if (bean instanceof Item){
					// don't do anything
				} else {
					notToBeConsideredRoot.add(bean);
				}
			}
		}
	}

	public boolean apply(Object obj) {
		return notToBeConsideredRoot.contains(obj);
	}
	
	public Set<Object> getChildren() {
		return notToBeConsideredRoot;
	}
}
