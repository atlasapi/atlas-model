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

import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfProperty;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Content extends Described {

	private ImmutableList<Clip> clips  = ImmutableList.of();
	
	protected Set<ContentGroup> containedIn = Sets.newHashSet();
	protected Set<String> containedInUris = Sets.newHashSet();
	
	public Content(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}

	public Content() { /* some legacy code still requires a default constructor */}
	
	public Set<String> getContainedInUris() {
		Set<String> merged = Sets.newHashSet();
		for (ContentGroup playlist : containedIn) {
			merged.add(playlist.getCanonicalUri());
		}
		merged.addAll(this.containedInUris);
		return merged;
	}
	
	public void setContainedInUris(Set<String> containedInUris) {
	    this.containedInUris = containedInUris;
	}
	
	@RdfProperty(relation = true)
	public Set<ContentGroup> getContainedIn() {
		return containedIn;
	}
	
	void addContainedIn(ContentGroup playlist) {
		containedIn.add(playlist);
		containedInUris.add(playlist.getCanonicalUri());
	}

	public void removeFrom(ContentGroup playlist) {
		containedIn.remove(playlist);
		containedInUris.remove(playlist.getCanonicalUri());
	}
	
	public List<Clip> getClips() {
		return clips;
	}
	
	public void setClips(Iterable<Clip> clips) {
		this.clips = ImmutableList.copyOf(clips);
		for (Clip clip : clips) {
			clip.setClipOf(this.getCanonicalUri());
		}
	}
	
	public void addClip(Clip clip) {
		List<Clip> all = Lists.newArrayList(clips);
		all.add(clip);
		setClips(all);
	}
}
