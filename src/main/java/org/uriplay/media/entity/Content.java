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

import java.util.Set;

import org.joda.time.DateTime;
import org.uriplay.content.rdf.annotations.RdfProperty;
import org.uriplay.media.vocabulary.DC;
import org.uriplay.media.vocabulary.PLAY;
import org.uriplay.media.vocabulary.PO;
import org.uriplay.media.vocabulary.SIOC;

import com.google.common.collect.Sets;

public class Content extends Description {

	private String title;
	
	private String description;
	
	private Set<String> genres = Sets.newHashSet();
	
	private Set<String> tags = Sets.newHashSet();
	
	protected Set<Playlist> containedIn = Sets.newHashSet();
	protected Set<String> containedInUris = Sets.newHashSet();
	
	private String publisher;
	private String image;
	private String thumbnail;
	
	private DateTime firstSeen;
	private DateTime lastFetched;
	
	public Content(String uri, String curie) {
		super(uri, curie);
	}
	
	public Content(String uri) {
		super(uri);
	}
	
	public Content() { /* some legacy code still requires a default constructor */ }
	
	public Set<String> getContainedInUris() {
		Set<String> merged = Sets.newHashSet();
		for (Playlist playlist : containedIn) {
			merged.add(playlist.getCanonicalUri());
		}
		merged.addAll(this.containedInUris);
		return merged;
	}
	
	public void setContainedInUris(Set<String> containedInUris) {
	    this.containedInUris = containedInUris;
	}
	
	public String getType() {
	    return this.getClass().getSimpleName();
	}

	public DateTime getLastFetched() {
        return lastFetched;
    }

    public void setLastFetched(DateTime lastFetched) {
        this.lastFetched = lastFetched;
    }
    
    public DateTime getFirstSeen() {
        return this.firstSeen;
    }

    public void setFirstSeen(DateTime firstSeen) {
        this.firstSeen = firstSeen;
    }
    
    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

	@RdfProperty(relation = true, namespace=PO.NS, uri = "genre")
	public Set<String> getGenres() {
		return this.genres;
	}
	
	@RdfProperty(relation = true)
	public Set<Playlist> getContainedIn() {
		return containedIn;
	}
	
	void addContainedIn(Playlist playlist) {
		containedIn.add(playlist);
		containedInUris.add(playlist.getCanonicalUri());
	}

	public void removeFrom(Playlist playlist) {
		containedIn.remove(playlist);
		containedInUris.remove(playlist.getCanonicalUri());
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	@RdfProperty(namespace = DC.NS)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@RdfProperty(relation = true, namespace=SIOC.NS, uri="topic")
	public Set<String> getTags() {
		return tags;
	}
	
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	@RdfProperty(namespace = DC.NS)
	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	    
    @RdfProperty(namespace = PLAY.NS)
    public String getImage() {
		return image;
	}
    
    @RdfProperty(namespace = PLAY.NS)
    public String getThumbnail() {
		return thumbnail;
	}
    
    public void setImage(String image) {
		this.image = image;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@RdfProperty(namespace = DC.NS)
	public String getTitle() {
		return this.title;
	}
}
