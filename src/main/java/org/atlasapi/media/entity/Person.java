package org.atlasapi.media.entity;

public class Person extends Identified {
    
    public static final String BASE_URI = "http://people.atlasapi.org/%s/%s";

	protected String name;
	protected String profileLink;
	
	public Person() { /* required for legacy code */ }
	
	public Person(String uri) {
		super(uri);
	}
	
	public String name() {
		return name;
	}
	
	public String profileLink() {
	    return profileLink;
	}
	
	public Person withName(String name) {
	    this.name = name;
	    return this;
	}
	
	public Person withProfileLink(String profileLink) {
	    this.profileLink = profileLink;
	    return this;
	}
	
	public static String formatForUri(String key) {
	    return key.toLowerCase().replace(' ', '_');
	}
}
