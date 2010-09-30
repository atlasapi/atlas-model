package org.atlasapi.media.entity;

import org.atlasapi.content.rdf.annotations.RdfClass;
import org.atlasapi.media.vocabulary.PO;

@RdfClass(namespace = PO.NS)
public class Clip extends Item {

	private Content clipOf;
	
	public Clip(String uri, String curie, Publisher publisher) {
		super(uri, curie, publisher);
	}
	
	public void setClipOf(Content clipOf) {
		this.clipOf = clipOf;
	}
	
	public Content getClipOf() {
		return clipOf;
	}
	
	public Clip() {}
}
