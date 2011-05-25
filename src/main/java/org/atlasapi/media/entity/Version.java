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

package org.atlasapi.media.entity;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;
import org.joda.time.Duration;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Version extends Identified {

	private Set<Encoding> manifestedAs = Sets.newLinkedHashSet();

	private Set<Broadcast> broadcasts = Sets.newLinkedHashSet();
	
	private Integer publishedDuration;
	
	private Integer duration;
	
	private Publisher provider;
	
	private Restriction restriction;
	
	public void setProvider(Publisher provider) {
		this.provider = provider;
	}
	
	public Publisher getProvider() {
		return provider;
	}
	
	@RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "manifestedAs", relation = true)
    public Set<Encoding> getManifestedAs() {
		return manifestedAs;
	}

	public void setManifestedAs(Set<Encoding> manifestedAs) {
		this.manifestedAs = manifestedAs;
	}
	
	public void addManifestedAs(Encoding encoding) {
	    checkNotNull(encoding);
		manifestedAs.add(encoding);
	}
	
	@RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "transmittedAt", relation = true)
    public Set<Broadcast> getBroadcasts() {
		return broadcasts;
	}

	public void setBroadcasts(Set<Broadcast> broadcasts) {
		this.broadcasts = broadcasts;
	}
	
	public void addBroadcast(Broadcast broadcast) {
		checkNotNull(broadcast);
		broadcasts.add(broadcast);
	}
	
    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, relation=false)
    public Integer getPublishedDuration() { 
        return this.publishedDuration;
    }
    
    public void setPublishedDuration(Integer publishedDuration) {
		this.publishedDuration = publishedDuration;
	}
    
    @RdfProperty(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, relation=false)
    public Integer getDuration() { 
        return this.duration;
    }
 
    public void setDuration(Duration duration) {
		this.duration = (int) duration.getStandardSeconds();
	}

	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	public Restriction getRestriction() {
		return restriction;
	}
	
	public Version copy() {
	    return copyWithBroadcasts(Sets.newHashSet(Iterables.transform(broadcasts, Broadcast.COPY)));
	}
	
	public Version copyWithBroadcasts(Set<Broadcast> broadcasts) {
	    Version copy = new Version();
        Identified.copyTo(this, copy);
        copy.broadcasts = broadcasts;
        copy.duration = duration;
        copy.manifestedAs = Sets.newHashSet(Iterables.transform(manifestedAs, Encoding.COPY));
        copy.provider = provider;
        copy.publishedDuration = publishedDuration;
        if (restriction != null) {
            copy.restriction = restriction.copy();
        }
        return copy;
	}
	
	public final static Function<Version, Version> COPY = new Function<Version, Version>() {
        @Override
        public Version apply(Version input) {
            return input.copy();
        }
    };
}
