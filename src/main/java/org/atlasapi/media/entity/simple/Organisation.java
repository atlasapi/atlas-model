package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableList;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="Organisation", namespace=PLAY_SIMPLE_XML.NS)
public class Organisation extends Description {
    
    private List<Person> members;
    
    public Organisation() {
    }
    
    public List<Person> members() {
        return members;
    }
    
    public void setMembers(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }

    @Override
    public Organisation copy() {
        Organisation copy = new Organisation();
        super.copyTo(copy);
        copy.setMembers(members);
        return copy;
    }

}
