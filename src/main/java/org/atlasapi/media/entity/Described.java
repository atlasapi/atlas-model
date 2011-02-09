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

import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DC;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;
import org.atlasapi.media.vocabulary.SIOC;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public class Described extends Identified {

	private String title;
	
	private String description;
		
	private MediaType mediaType = MediaType.VIDEO;
	private Specialization specialization;
	
	private ImmutableSet<String> genres = ImmutableSet.of();
	private Set<String> tags = Sets.newHashSet();
	
	protected Publisher publisher;
	private String image;
	private String thumbnail;
	
	private DateTime firstSeen;
	private DateTime lastFetched;
	private DateTime thisOrChildLastUpdated;
	
	public Described(String uri, String curie, Publisher publisher) {
		super(uri, curie);
		this.publisher = publisher;
	}
	
	public Described(String uri, String curie) {
		this(uri, curie, null);
	}
	
	public Described(String uri) {
		super(uri);
	}
	
	public Described() { /* some legacy code still requires a default constructor */ }
	
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
    
    public void setGenres(Iterable<String> genres) {
        this.genres = ImmutableSet.copyOf(genres);
    }

	@RdfProperty(relation = true, namespace=PO.NS, uri = "genre")
	public Set<String> getGenres() {
		return this.genres;
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

	public Set<String> getTags() {
		return tags;
	}
	
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	@RdfProperty(namespace = DC.NS)
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	    
    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS)
    public String getImage() {
		return image;
	}
    
    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS)
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
	
	public DateTime getThisOrChildLastUpdated() {
        return thisOrChildLastUpdated;
    }
	
	public void setThisOrChildLastUpdated(DateTime thisOrChildLastUpdated) {
        this.thisOrChildLastUpdated = thisOrChildLastUpdated;
    }
	
	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}
	
	public MediaType getMediaType() {
		return this.mediaType;
	}
	
	public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }
}
