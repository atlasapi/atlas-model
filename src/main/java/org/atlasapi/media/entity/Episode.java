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

import static org.atlasapi.media.entity.ParentRef.parentRefFrom;

import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;

import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Episode extends Item {

    private Integer seriesNumber;
	private Integer episodeNumber;
	private Integer partNumber;
	private Boolean special = null;
	
    private ParentRef seriesRef;

	public Episode(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
	public Episode() { }
	
	public Integer getPartNumber() {
	    return this.partNumber;
	}

	@RdfProperty(namespace=PO.NS, uri="position")
	public Integer getEpisodeNumber() {
		return episodeNumber;
	}
	
	@RdfProperty(namespace=PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri="seasonPosition")
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	
	public Boolean getSpecial() {
	    return special;
	}
	
	public void setSpecial(Boolean special) {
	    this.special = special;
	}

    public void setPartNumber(Integer partNumber) {
        this.partNumber = partNumber;
    }
	
	public void setEpisodeNumber(Integer position) {
		this.episodeNumber = position;
	}
	
	public void setSeriesNumber(Integer position) {
		this.seriesNumber = position;
	}
	
    public void setSeriesRef(ParentRef seriesRef) {
        this.seriesRef = seriesRef;
    }
    
    public void setSeries(Series series) {
        setSeriesRef(parentRefFrom(series));
    }

	public ParentRef getSeriesRef() {
		return seriesRef;
	}
	
	@Override
	public Episode copy() {
		Episode episode = new Episode();
		Item.copyTo(this,episode);
		episode.episodeNumber = episodeNumber;
		episode.seriesNumber = seriesNumber;
		episode.seriesRef = seriesRef;
		episode.special = special;
		return episode;
	}

}
