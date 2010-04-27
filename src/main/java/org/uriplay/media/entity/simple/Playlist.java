package org.uriplay.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY.NS)
@XmlType(name="list", namespace=PLAY.NS)
public class Playlist extends Description {

	private Set<Item> items = Sets.newHashSet();
	private Set<Playlist> playlists = Sets.newHashSet();

	public void addItem(Item item) {
		items.add(item);
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="items")
	@XmlElement(namespace=PLAY.NS, name="item")
	public Set<Item> getItems() {
		return items;
	}
	
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="playlists")
	@XmlElement(namespace=PLAY.NS, name="playlist")
	public Set<Playlist> getPlaylists() {
		if (playlists.isEmpty()) {
			return null;
		}
		return playlists;
	}
	
	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}
	
}
