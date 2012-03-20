package org.atlasapi.persistence.logging;

public class EntryExpiredException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EntryExpiredException(String id) {
		super("Could not find entry with id " + id);
	}

}
