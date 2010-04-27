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

import org.jherd.rdf.annotations.RdfClass;
import org.jherd.rdf.annotations.RdfProperty;
import org.uriplay.media.vocabulary.PO;

import com.google.common.collect.Sets;

/**
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 * @author Chris Jackson
 */
@RdfClass(namespace = PO.NS)
public class Brand extends Playlist {

    private Set<String> genres = Sets.newHashSet();

    @Override
    @RdfProperty(relation = true, namespace = PO.NS, uri = "episode")
    public List<Item> getItems() {
        return super.getItems();
    }

    @Override
    public void addItem(Item item) {
        super.addItem(item);
        
        if (item instanceof Episode) {
            ((Episode) item).setBrand(this);
        }
    }

    @RdfProperty(relation = true, namespace = PO.NS, uri = "genre")
    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Brand toSummary() {
        Brand summary = new Brand();
        summary.setCanonicalUri(this.getCanonicalUri());
        summary.setCurie(this.getCurie());
        summary.setTitle(this.getTitle());
        summary.setDescription(this.getDescription());
        return summary;
    }
}
