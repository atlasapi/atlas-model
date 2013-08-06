package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.entity.simple.ContentIdentifier.BrandIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.EpisodeIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.FilmIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.ItemIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.PersonIdentifier;
import org.atlasapi.media.entity.simple.ContentIdentifier.SeriesIdentifier;
import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;
import org.joda.time.DateTime;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="person", namespace=PLAY_SIMPLE_XML.NS)
public class Person extends Description {

	private String name;
	private String profileLink;
	private String role;
	private String displayRole;
	private String character;
	private List<ContentIdentifier> content = ImmutableList.of();
    private Set<ContentIdentifier> upcomingContent = ImmutableSet.of();
    private Set<ContentIdentifier> availableContent = ImmutableSet.of();
	private String givenName;
    private String familyName;
    private String gender;
    private DateTime birthDate;
    private String birthPlace;
    private Set<String> quotes;
	
	public Person() {
    }
	
	public String getName() {
		return name;
	}
	
	public String getRole() {
	    return role;
	}
	
	public void setRole(String role) {
	    this.role = role;
	}
    
    public String getDisplayRole() {
        return displayRole;
    }
    
    public void setDisplayRole(String displayRole) {
        this.displayRole = displayRole;
    }
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getProfileLinks() {
        return aliases;
    }
	
	public void setProfileLinks(Set<String> profileLinks) {
        this.setAliases(profileLinks);
    }
	
	public List<ContentIdentifier> getContent() {
        return content;
    }
	
    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
	public void setContent(List<ContentIdentifier> content) {
        this.content = content;
    }
	
    public void setUpcomingContent(Iterable<ContentIdentifier> filteredRefs) {
        this.upcomingContent = ImmutableSet.copyOf(filteredRefs);
    }

    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="upcoming_content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    public Set<ContentIdentifier> getUpcomingContent() {
        return upcomingContent;
    }
    
    public void setAvailableContent(Iterable<ContentIdentifier> filteredRefs) {
        this.availableContent = ImmutableSet.copyOf(filteredRefs);
    }

    @XmlElementWrapper(namespace=PLAY_SIMPLE_XML.NS, name="available_content")
    @XmlElements({ 
        @XmlElement(name = "item", type = ItemIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "episode", type = EpisodeIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "film", type = FilmIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "person", type = PersonIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "series", type = SeriesIdentifier.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "brand", type = BrandIdentifier.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    public Set<ContentIdentifier> getAvailableContent() {
        return availableContent;
    }
	
	public String getProfileLink() {
        return profileLink;
    }

    public void setProfileLink(String profileLink) {
        this.profileLink = profileLink;
    }

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

    public void setQuotes(Set<String> quotes) {
        this.quotes = quotes;
    }

    public Person copy() {
	    Person person = new Person();
	    super.copyTo(person);
	    person.name = name;
	    person.profileLink = profileLink;
	    person.role = role;
	    person.displayRole = displayRole;
	    person.character = character;
	    person.givenName = givenName;
	    person.familyName = familyName;
	    person.gender = gender;
	    person.birthDate = birthDate;
	    person.birthPlace = birthPlace;
	    person.quotes = quotes;
	    return person;
	}
	
    public String getCharacter() {
        return character;
    }
    
    public void setCharacter(String character) {
        this.character = character;
    }
}
