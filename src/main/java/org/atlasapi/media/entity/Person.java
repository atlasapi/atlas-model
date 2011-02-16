package org.atlasapi.media.entity;

import org.joda.time.DateTime;

public class Person extends Identified {
    
    public static final String BASE_URI = "http://people.atlasapi.org/%s/%s";

	private String name;
	private String profileLink;
	private DateTime lastUpdated;
	
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
	
	public DateTime lastUpdated() {
	    return lastUpdated;
	}
	
	public Person withLastUpdated(DateTime lastUpdated) {
	    this.lastUpdated = lastUpdated;
	    return this;
	}
	
	public Person withName(String name) {
	    this.name = name;
	    return this;
	}
	
	public Person withProfileLink(String profileLink) {
	    this.profileLink = profileLink;
	    return this;
	}
	
	public static String uriFor(String name, Publisher publisher) {
	    return String.format(BASE_URI, publisher.key(), name.toLowerCase().replace(" ", "_"));
	}
}
