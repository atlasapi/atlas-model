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
import org.atlasapi.media.vocabulary.DC;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;
import org.atlasapi.media.vocabulary.SIOC;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class Content extends Description {

	private String title;
	
	private String description;
	
	private MediaType mediaType = MediaType.VIDEO;
	private Specialization specialization;
	
    private Set<String> genres = Sets.newHashSet();
	
	private Set<String> tags = Sets.newHashSet();
	
	protected Set<Playlist> containedIn = Sets.newHashSet();
	protected Set<String> containedInUris = Sets.newHashSet();
	
	private ImmutableList<Clip> clips  = ImmutableList.of();
	
	protected Publisher publisher;
	private String image;
	private String thumbnail;
	
	private DateTime firstSeen;
	private DateTime lastFetched;
	private DateTime thisOrChildLastUpdated;
	
	public Content(String uri, String curie, Publisher publisher) {
		super(uri, curie);
		this.publisher = publisher;
	}
	
	public Content(String uri, String curie) {
		this(uri, curie, null);
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
