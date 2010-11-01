package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.media.vocabulary.PO;

@RdfClass(namespace = PO.NS)
public class Clip extends Item {

	private String clipOf;
	
	public Clip(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
	public void setClipOf(String clipOf) {
		this.clipOf = clipOf;
	}
	
	public String getClipOf() {
		return clipOf;
	}
	
	public Clip() {}
}
