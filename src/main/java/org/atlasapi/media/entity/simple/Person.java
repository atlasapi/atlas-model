package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="person", namespace=PLAY_SIMPLE_XML.NS)
public class Person extends Aliased {

	private String name;
	private final String type;
	
	public Person() {
        this.type = this.getClass().getSimpleName().toLowerCase();
    }
	
	public String getName() {
		return name;
	}
	
	public String getType() {
	    return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void copyTo(Person person) {
	    super.copyTo(person);
	    person.name = name;
	}
}
