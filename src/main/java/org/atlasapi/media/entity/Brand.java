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

import java.util.List;
import java.util.Set;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.PO;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

/**
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Chris Jackson
 */
@RdfClass(namespace = PO.NS, uri = "List")
public class Brand extends Container<Episode> {
    
    private ImmutableMap<String, Series> seriesLookup = ImmutableMap.of();

    public Brand(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
    
    public Brand() { /* some legacy code still requires a default constructor */ }

	@Override
	@RdfProperty(relation = true, namespace = PO.NS, uri = "episode")
	public ImmutableList<Episode> getContents() {
		return (ImmutableList<Episode>) super.getContents();
	}

	@Override
    public Brand toSummary() {
        Brand summary = new Brand(this.getCanonicalUri(), this.getCurie(), this.getPublisher());
        summary.setTitle(this.getTitle());
        summary.setDescription(this.getDescription());
        return summary;
    }
	
	public Set<Series> getSeries() {
		List<Series> all = Lists.newArrayList();
		for (Episode episode : contents) {
			Series series = episode.getSeries();
			if (series != null) {
				all.add(series);
			}
		}
		return Sets.newHashSet(Ordering.from(DESCENDING_LAST_UPDATED).sortedCopy(all));
	}
	
	@Override
	public Container<Episode> copy() {
	    Brand copy = new Brand();
	    Container.copyTo(this, copy);
	    return copy;
	}
	
	public void setSeriesLookup(Iterable<Series> series) {
        seriesLookup = Maps.uniqueIndex(series, Identified.TO_URI);
        for (Series serie : series) {
            serie.setParent(this);
        }
	}
	
	public Series getSeriesFromLookup(String uri) {
	    return seriesLookup.get(uri);
	}

    public static final Function<Brand, Brand> COPY = new Function<Brand, Brand>() {
        @Override
        public Brand apply(Brand input) {
            return (Brand) input.copy();
        }
    };
}
