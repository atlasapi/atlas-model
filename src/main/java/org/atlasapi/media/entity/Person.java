package org.atlasapi.media.entity;

import java.util.Set;

import org.joda.time.DateTime;

import com.google.common.base.Function;
import com.google.common.collect.Sets;
import com.metabroadcast.common.url.UrlEncoding;

public class Person extends ContentGroup {
    
    public static final String BASE_URI = "http://people.atlasapi.org/%s/%s";
    
    private String givenName;
    private String familyName;
    private String gender;
    private DateTime birthDate;
    private String birthPlace;
    private Set<String> quotes = Sets.newHashSet();
    
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Set<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Iterable<String> quotes) {
        this.quotes = Sets.newHashSet(quotes);
    }
    
    public void addQuote(String quote) {
        this.quotes.add(quote);
    }

	public Person() { /* required for legacy code */ }
	
	public Person(String uri, String curie, Publisher publisher) {
         super(ContentGroup.Type.PERSON, uri, curie, publisher);
    }
	
	public String name() {
		return this.getTitle();
	}
	
	public Set<String> profileLinks() {
	    return this.getAliasUrls();
	}
	
	public Person withProfileLink(String profileLink) {
	    this.addAliasUrl(profileLink);
	    return this;
	}
	
	public Person withProfileLinks(Set<String> profileLinks) {
        this.setAliasUrls(profileLinks);
        return this;
    }
	
	public Person withName(String name) {
	    this.setTitle(name);
	    return this;
	}
	
	public static String formatForUri(String key) {
        return UrlEncoding.encode(key.toLowerCase().replace(' ', '_'));
    }
	
	@Override
	public Person copy() {
	    Person copy = new Person();
	    ContentGroup.copyTo(this, copy);
	    copy.setGivenName(givenName);
	    copy.setFamilyName(familyName);
	    copy.setGender(gender);
	    copy.setBirthDate(birthDate);
	    copy.setBirthPlace(birthPlace);
	    copy.setQuotes(quotes);
	    return copy;
	}
	
	public final static Function<Person, Person> COPY = new Function<Person, Person>() {
        @Override
        public Person apply(Person input) {
            return (Person) input.copy();
        }
    };
}
