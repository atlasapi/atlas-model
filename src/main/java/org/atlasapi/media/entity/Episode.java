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

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;
import org.atlasapi.media.vocabulary.PO;

/**
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Lee Denison (lee@metabroadcast.com)
 */
@RdfClass(namespace = PO.NS)
public class Episode extends Item {

	private Integer episodeNumber;
	private Integer seriesNumber;
	private Brand brand;
	private Series series;

	public Episode(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
	public Episode() { }

	@RdfProperty(namespace=PO.NS, uri="position")
	public Integer getEpisodeNumber() {
		return episodeNumber;
	}
	
	@RdfProperty(namespace=PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri="seasonPosition")
	public Integer getSeriesNumber() {
		return seriesNumber;
	}
	
	public void setEpisodeNumber(Integer position) {
		this.episodeNumber = position;
	}
	
	public void setSeriesNumber(Integer position) {
		this.seriesNumber = position;
	}

	public void setBrand(Brand brand) {
        this.brand = brand;
        this.addContainedIn(brand);
    }
    
    public Brand getBrand() {
		if (brand == null) {
			return null;
		}
        return this.brand.toSummary();
    }
    
    public Series getSeriesSummary() {
    	if (series == null) {
    		return null;
    	}
        return series.toSummary();
    }
    
    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
    
    public void setSeries(Series series) {
		this.series = series;
	}

	public Series getHydratedSeries() {
		if (series != null && !series.isASummary()) {
			return series;
		}
		return null;
	}
}
