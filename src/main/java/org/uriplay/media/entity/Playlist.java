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
import org.joda.time.DateTime;
import org.uriplay.media.vocabulary.DCTERMS;
import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.soy.common.collect.Lists;
import com.hp.hpl.jena.vocabulary.DC;

/**
 * A play:List (playlist) - rough cut.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@RdfClass(namespace = PLAY.NS, uri = "List")
public class Playlist extends Description {
	
    private String title;

    private String description;
    
    private String publisher;

    private List<Item> items = Lists.newArrayList();
    private List<String> itemUris = Lists.newArrayList();

    private List<Playlist> playlists = Lists.newArrayList();
    private List<String> playlistUris = Lists.newArrayList();

    private Set<Playlist> containedIn = Sets.newLinkedHashSet();
    private Set<String> containedInUris = Sets.newLinkedHashSet();
    
    private DateTime firstSeen;
    private DateTime lastFetched;

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public List<Item> getItems() { 
        return this.items; 
    }
    
    public List<String> getItemUris() {
        List<String> uris = Lists.newArrayList();
        for (Item item: items) {
            uris.add(item.getCanonicalUri());
        }
        for (String itemUri: itemUris) {
            if (! uris.contains(itemUri)) {
                uris.add(itemUri);
            }
        }
        return uris;
    }
    
    public void setItemUris(List<String> itemUris) {
        this.itemUris = itemUris;
    }

    public void setItems(List<Item> items) { 
    	SetView<Item> evictedItems = Sets.difference(Sets.newHashSet(this.items), Sets.newHashSet(items));
    	for (Item item : evictedItems) {
			item.removeFrom(this);
		}
    	this.items.clear();
    	this.itemUris.clear();
        for (Item item : items) {
            addItem(item);
        }
    }

    public void addItems(Item... items) {
        for (Item item : items) {
            addItem(item);
        }
    }

    public void addItem(Item item) {
        this.items.add(item);
        item.addContainedIn(this);
        itemUris.add(item.getCanonicalUri());
    }

    @RdfProperty(relation = true, namespace = DCTERMS.NS, uri = "hasPart")
    public List<Playlist> getPlaylists() { 
        return this.playlists; 
    }

    public void setPlaylists(List<Playlist> playlists) { 
        for (Playlist playlist : playlists) {
        	addPlaylist(playlist);
        }
    }

    public List<String> getPlaylistUris() {
        List<String> uris = Lists.newArrayList();
        for (Playlist playlist : playlists) {
            uris.add(playlist.getCanonicalUri());
        }
        for (String playlistUri: playlistUris) {
            if (! uris.contains(playlistUri)) {
                uris.add(playlistUri);
            }
        }
        return uris;
    }

    public void setPlaylistUris(List<String> playlistUris) {
        this.playlistUris = playlistUris;
    }

    public void addPlaylist(Playlist playlist) {
       this.playlists.add(playlist);
       this.playlistUris.add(playlist.getCanonicalUri());
       playlist.containedIn.add(this);
       playlist.containedInUris.add(this.getCanonicalUri());
    }

    @RdfProperty(namespace = DC.NS)
    public String getTitle() {
        return this.title;
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

    public Set<String> getContainedInUris() {
        Set<String> uris = Sets.newHashSet();
        for (Playlist playlist : containedIn) {
            uris.add(playlist.getCanonicalUri());
        }
        uris.addAll(containedInUris);
        return uris;
    }

    public void setContainedInUris(Set<String> containedInUris) {
        this.containedInUris = containedInUris;
    }

    public Set<Playlist> getContainedIn() {
        return containedIn;
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
    
    public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
    
    @RdfProperty(namespace = DC.NS)
    public String getPublisher() {
		return publisher;
	}
}
