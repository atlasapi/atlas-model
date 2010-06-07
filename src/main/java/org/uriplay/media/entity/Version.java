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

import java.util.Set;

import org.joda.time.Duration;
import org.uriplay.content.rdf.annotations.RdfClass;
import org.uriplay.content.rdf.annotations.RdfProperty;
import org.uriplay.media.vocabulary.PLAY;
import org.uriplay.media.vocabulary.PO;

import com.google.common.collect.Sets;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Version extends Description {

	private Set<Encoding> manifestedAs = Sets.newHashSet();

	private Set<Broadcast> broadcasts = Sets.newHashSet();
	
	private Integer publishedDuration;
	
	private Integer duration;
	
	private String rating;
	
	private String ratingText;

    @RdfProperty(namespace = PLAY.NS, relation=true)
	public String getRating() {
		return rating;
	}
    
    public void setRating(String rating) {
		this.rating = rating;
	}

    @RdfProperty(namespace = PLAY.NS, relation=false)
	public String getRatingText() {
		return ratingText;
	}
    
    public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
	}
	
	@RdfProperty(namespace = PLAY.NS, uri = "manifestedAs", relation = true)
    public Set<Encoding> getManifestedAs() {
		return manifestedAs;
	}

	public void setManifestedAs(Set<Encoding> manifestedAs) {
		this.manifestedAs = manifestedAs;
	}
	
	public void addManifestedAs(Encoding encoding) {
		manifestedAs.add(encoding);
	}
	
	@RdfProperty(namespace = PLAY.NS, uri = "transmittedAt", relation = true)
    public Set<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(Set<Broadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}
	
	public void addBroadcast(Broadcast broadcast) {
		broadcasts.add(broadcast);
	}
	
    @RdfProperty(namespace = PLAY.NS, relation=false)
    public Integer getPublishedDuration() { 
        return this.publishedDuration;
    }
    
    public void setPublishedDuration(Integer publishedDuration) {
		this.publishedDuration = publishedDuration;
	}
    
    @RdfProperty(namespace = PLAY.NS, relation=false)
    public Integer getDuration() { 
        return this.duration;
    }
 
    public void setDuration(Duration duration) {
		this.duration = (int) duration.getStandardSeconds();
	}
}
