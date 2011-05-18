package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="person", namespace=PLAY_SIMPLE_XML.NS)
public class Person extends Aliased {

	private String name;
	private String profileLink;
	private String role;
	private String character;
	private List<Description> content;
	
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<String> getProfileLinks() {
        return aliases;
    }
	
	public void setProfileLinks(Set<String> profileLinks) {
        this.setAliases(profileLinks);
    }
	
	public List<Description> getContent() {
        return content;
    }
	
	public void setContent(List<Description> content) {
        this.content = content;
    }
	
	public Person copy() {
	    Person person = new Person();
	    super.copyTo(person);
	    person.name = name;
	    person.profileLink = profileLink;
	    person.role = role;
	    person.character = character;
	    return person;
	}
	
    public String getCharacter() {
        return character;
    }
    
    public void setCharacter(String character) {
        this.character = character;
    }
}
