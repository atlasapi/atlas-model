package org.atlasapi.media.entity;

public class Person extends Identified {

	private String name;
	
	public Person() { /* required for legacy code */ }
	
	public Person(String uri) {
		super(uri);
	}
	
	public String getName() {
		return name;
	}
}
