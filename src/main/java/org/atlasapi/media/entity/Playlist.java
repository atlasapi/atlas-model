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

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.content.rdf.annotations.RdfProperty;
import org.atlasapi.media.vocabulary.DCTERMS;
import org.atlasapi.media.vocabulary.PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * A play:List (playlist) - rough cut.
 * 
 * @author Robert Chatley (robert@metabroadcast.com)
 */
@RdfClass(namespace = PLAY_USE_IN_RDF_FOR_BACKWARD_COMPATIBILITY.NS, uri = "List")
public class Playlist extends Content {
	

	private List<Playlist> playlists = Lists.newArrayList();
    private List<String> playlistUris = Lists.newArrayList();

    private List<Item> items = Lists.newArrayList();
    private List<String> itemUris = Lists.newArrayList();

    public Playlist(String uri, String curie, Publisher publisher) {
    	super(uri, curie, publisher);
    }
    
    public Playlist(String uri, String curie) {
    	super(uri, curie);
    }
    
    public Playlist() {
    	/* some legacy code still requires a default constructor */
    }
    
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
            if (!uris.contains(itemUri)) {
                uris.add(itemUri);
            }
        }
        return uris;
    }
    
    public void setItemUris(List<String> itemUris) {
        this.itemUris = itemUris;
    }

    public void setItems(List<? extends Item> items) { 
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

    public void setPlaylists(Iterable<? extends Playlist> playlists) {
    	this.playlists.clear();
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

	public void addPlaylistUri(String uri) {
		playlistUris.add(uri);
	}
}
