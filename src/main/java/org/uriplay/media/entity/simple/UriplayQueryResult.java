package org.uriplay.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.uriplay.media.entity.simple.Playlist;
import org.uriplay.media.vocabulary.PLAY;

import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY.NS, name="uriplay")
@XmlType(name="uriplay", namespace=PLAY.NS)
public class UriplayQueryResult {

	private List<Item> items = Lists.newArrayList();
	private List<Playlist> playlists = Lists.newArrayList();

	public void addItem(Item item) {
		items.add(item);
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="items")
	@XmlElement(namespace=PLAY.NS, name="item")
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	@XmlElementWrapper(namespace=PLAY.NS, name="playlists")
	@XmlElement(namespace=PLAY.NS, name="playlist")
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
}
