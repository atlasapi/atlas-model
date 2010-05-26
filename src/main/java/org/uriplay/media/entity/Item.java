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

package org.uriplay.media.entity;

import java.util.List;
import java.util.Set;

import org.uriplay.content.rdf.annotations.RdfClass;
import org.uriplay.content.rdf.annotations.RdfProperty;
import org.uriplay.media.TransportType;
import org.uriplay.media.vocabulary.DC;
import org.uriplay.media.vocabulary.PO;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 * @author John Ayres (john@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Item extends Content {
	
	private Set<Version> versions = Sets.newHashSet();
	
	private boolean isLongForm = false;
	
	public Item(String uri, String curie) {
		super(uri, curie);
	}
	
	public Item() { }
	
	@RdfProperty(namespace = DC.NS)
	public boolean getIsLongForm() {
		return isLongForm;
	}

	public void setIsLongForm(boolean isLongForm) {
		this.isLongForm = isLongForm;
	}

	public void addVersion(Version version) {
		versions.add(version);
	}

	@RdfProperty(relation = true, uri="version")
	public Set<Version> getVersions() {
		return versions;
	}
	
	public void setVersions(Set<Version> versions) {
		this.versions = versions;
	}

	public boolean removeVersion(Version version) {
		return versions.remove(version);
	}
	
	
	public void setContainedIn(Set<Playlist> containedIn) {
		for (Playlist parent : containedIn) {
			parent.addItem(this);
		}
	}

	public boolean isAvailable() {
		for (Location location : locations()) {
			if (location.getAvailable()) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmbeddable() {
		for (Location location : locations()) {
			if (location.getTransportType() != null && TransportType.EMBEDOBJECT.equals(location.getTransportType())) {
				return true;
			}
		}
		return false;
	}
	
	private List<Location> locations() {
		List<Location> locations = Lists.newArrayList();
		for (Version version : getVersions()) {
			for (Encoding encoding : version.getManifestedAs()) {
				for (Location location : encoding.getAvailableAt()) {
					locations.add(location);
				}
			}
		}
		
		return locations;
	}

	public Brand primaryBrand() {
		Set<Playlist> playlists = getContainedIn();
		if (playlists == null || playlists.isEmpty()) {
			return null;
		}
		for (Playlist playlist : playlists) {
			if (playlist instanceof Brand) {
				return (Brand) playlist;
			}
		}
		return null;
	}
}
