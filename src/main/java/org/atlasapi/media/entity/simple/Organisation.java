package org.atlasapi.media.entity.simple;

import java.util.List;
import java.util.Set;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="Organisation", namespace=PLAY_SIMPLE_XML.NS)
public class Organisation extends Description {
    
    private List<Person> members = Lists.newArrayList();
    private Set<String> alternativeTitles = Sets.newHashSet();

    public Organisation() {
    }

    public Set<String> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(Iterable<String> alternativeTitles) {
        this.alternativeTitles = ImmutableSet.copyOf(alternativeTitles);
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
        copy.setAlternativeTitles(alternativeTitles);
        return copy;
    }

}
