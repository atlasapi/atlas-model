package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.media.vocabulary.PO;

@RdfClass(namespace = PO.NS)
public class Song extends Item {

	private String isrc;
	
	public Song(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
	public void setIsrc(String isrc) {
		this.isrc = isrc;
	}

    public String getIsrc() {
        return isrc;
    }
	
	public Song() {}
	
	@Override
	public Song copy() {
	    Song song = new Song();
	    Item.copyTo(this, song);
	    song.isrc = isrc;
	    return song;
	}
}
