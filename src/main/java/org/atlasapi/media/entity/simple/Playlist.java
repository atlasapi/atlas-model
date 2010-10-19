package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="list", namespace=PLAY_SIMPLE_XML.NS)
public class Playlist extends Description {

	private List<Item> items = Lists.newArrayList();
	private List<Playlist> playlists = Lists.newArrayList();

	public void addItem(Item item) {
		items.add(item);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="items")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="item")
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public void addPlaylist(Playlist playlist) {
		playlists.add(playlist);
	}
	
	@XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="playlists")
	@XmlElement(namespace=PLAY_SIMPLE_XML.NS, name="playlist")
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
}
