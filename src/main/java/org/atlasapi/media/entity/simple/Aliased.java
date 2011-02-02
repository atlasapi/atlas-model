package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

public abstract class Aliased extends Identified {

	protected Set<String> aliases = Sets.newHashSet();
	
	public Aliased(String uri) {
		this.uri = uri;
	}
	
	public Aliased() { /* required for XML/JSON tools */	}
	
	public void setAliases(Set<String> aliases) {
		this.aliases = aliases;
	}
	
	@XmlElementWrapper(name="aliases")
	@XmlElement(name="alias")
	public Set<String> getAliases() {
		return aliases;
	}
	
	protected void copyTo(Aliased destination) {
        Preconditions.checkNotNull(destination);
        
        super.copyTo(destination);
        
        destination.setAliases(getAliases());
    }
	
}
