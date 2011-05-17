package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="people")
@XmlType(name="people", namespace=PLAY_SIMPLE_XML.NS)
public class PeopleQueryResult {
    
    private List<Person> people = Lists.newArrayList();
    
    public void add(Person person) {
        people.add(person);
    }
    
    @XmlElements(@XmlElement(name = "person", type=Person.class, namespace=PLAY_SIMPLE_XML.NS))
    public List<Person> getPeople() {
        return people;
    }
    
    public void setPeople(Iterable<Person> people) {
        this.people = Lists.newArrayList(people);
    }
    
    public boolean isEmpty() {
        return people.isEmpty();
    }

    @Override
    public int hashCode() {
        return people.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this instanceof PeopleQueryResult) {
            PeopleQueryResult other = (PeopleQueryResult) obj;
            return people.equals(other.people);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(people).toString();
    }
}
