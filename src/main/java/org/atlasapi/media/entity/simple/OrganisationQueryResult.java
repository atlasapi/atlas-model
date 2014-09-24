package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="organisations")
@XmlType(name="organisations", namespace=PLAY_SIMPLE_XML.NS)
public class OrganisationQueryResult {
    
    private List<Organisation> organisations = Lists.newArrayList();

    public void add(Organisation organisation) {
        organisations.add(organisation);
    }

    @XmlElements({ 
        @XmlElement(name = "organisation", type = Organisation.class, namespace=PLAY_SIMPLE_XML.NS)
    })
    public List<Organisation> getOrganisations() {
        return organisations;
    }
    
    public void setOrganisations(Iterable<Organisation> organisations) {
        this.organisations = Lists.newArrayList(organisations);
    }
    
    public boolean isEmpty() {
        return organisations.isEmpty();
    }

    @Override
    public int hashCode() {
        return organisations.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this instanceof OrganisationQueryResult) {
            OrganisationQueryResult other = (OrganisationQueryResult) obj;
            return organisations.equals(other.organisations);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(organisations).toString();
    }
}
