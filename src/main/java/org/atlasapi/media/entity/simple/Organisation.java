package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="Organisation", namespace=PLAY_SIMPLE_XML.NS)
public class Organisation extends Description {
    
    private List<Person> members = Lists.newArrayList();
    private Set<Topic> eventGroups = Sets.newHashSet();
    
    public Organisation() {
    }
    
    public List<Person> members() {
        return members;
    }
    
    public void setMembers(Iterable<Person> members) {
        this.members = ImmutableList.copyOf(members);
    }

    public Set<Topic> eventGroups() {
        return eventGroups;
    }
    
    public void setEventGroups(Iterable<Topic> eventGroups) {
        this.eventGroups = Sets.newHashSet(eventGroups);
    }

    @Override
    public Organisation copy() {
        Organisation copy = new Organisation();
        super.copyTo(copy);
        copy.setMembers(members);
        copy.setEventGroups(eventGroups);
        return copy;
    }

}
