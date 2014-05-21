package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.collect.ImmutableSet;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS)
@XmlType(name="group", namespace=PLAY_SIMPLE_XML.NS)
public class ChannelGroupSummary {

    private String id;
    private Set<Alias> aliases;
    private String title;
    
    public ChannelGroupSummary() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElementWrapper(name="aliases")
    @XmlElement(name="alias")
    public Set<Alias> getAliases() {
        return aliases;
    }

    public void setAliases(Iterable<Alias> aliases) {
        this.aliases = ImmutableSet.copyOf(aliases);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
