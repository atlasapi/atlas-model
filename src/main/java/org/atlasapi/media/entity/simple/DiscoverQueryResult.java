package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="content")
@XmlType(name="content", namespace=PLAY_SIMPLE_XML.NS)
public class DiscoverQueryResult {
    
    private final List<Description> descriptions;
    
    public DiscoverQueryResult(Iterable<? extends Description> descriptions) {
        this.descriptions = ImmutableList.copyOf(descriptions);
    }
    
    @XmlElements({ 
        @XmlElement(name = "item", type = Item.class, namespace=PLAY_SIMPLE_XML.NS),
        @XmlElement(name = "playlist", type = Playlist.class, namespace=PLAY_SIMPLE_XML.NS) 
    })
    
    public List<Description> getResults() {
        return descriptions;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(descriptions).toString();
    }
}
