package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.atlasapi.media.entity.simple.Alias;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public abstract class Aliased extends Identified {

    protected Set<String> aliases = Sets.newHashSet();
    protected Set<Alias> v4Aliases = Sets.newHashSet();

    public Aliased(String uri) {
        super(uri);
    }

    public Aliased() { /* required for XML/JSON tools */	}

    public void setAliases(Set<String> aliases) {
        this.aliases = ImmutableSet.copyOf(aliases);
    }

    public void setV4Aliases(Set<Alias> v4Aliases) {
        this.v4Aliases = ImmutableSet.copyOf(v4Aliases);
    }

    @XmlElementWrapper(name="aliases")
    @XmlElement(name="alias")
    public Set<String> getAliases() {
        return aliases;
    }

    @XmlElementWrapper(name="v4aliases")
    @XmlElement(name="alias")
    public Set<Alias> getV4Aliases() {
        return v4Aliases;
    }

    protected void copyTo(Aliased destination) {
        Preconditions.checkNotNull(destination);

        super.copyTo(destination);

        destination.setAliases(getAliases());
    }

}
