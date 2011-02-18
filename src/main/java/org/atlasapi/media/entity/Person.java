package org.atlasapi.media.entity;

import java.util.Set;

public class Person extends ContentGroup {
    
    public static final String BASE_URI = "http://people.atlasapi.org/%s/%s";

	public Person() { /* required for legacy code */ }
	
	public Person(String uri, String curie, Publisher publisher) {
         super(uri, curie, publisher);
    }
	
	public String name() {
		return this.getTitle();
	}
	
	public Set<String> profileLinks() {
	    return this.getAliases();
	}
	
	public Person withProfileLink(String profileLink) {
	    this.addAlias(profileLink);
	    return this;
	}
	
	public Person withProfileLinks(Set<String> profileLinks) {
        this.setAliases(profileLinks);
        return this;
    }
	
	public Person withName(String name) {
	    this.setTitle(name);
	    return this;
	}
	
	public static String formatForUri(String key) {
        return key.toLowerCase().replace(' ', '_');
    }
}
