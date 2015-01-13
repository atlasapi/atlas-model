package org.atlasapi.media.entity.simple;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;


public abstract class Version {
	
	private Integer duration;
	private Integer publishedDuration;
	private Restriction restriction;
	private Boolean is3d;
	private Set<Alias> v4Aliases = Sets.newHashSet();
	
    @XmlElementWrapper(name="v4aliases")
    @XmlElement(name="alias")
	public Set<Alias> getV4Aliases() {
	    return v4Aliases;
	}
	
	public void setV4Aliases(Iterable<Alias> aliases) {
	    this.v4Aliases = ImmutableSet.copyOf(aliases);
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Integer getPublishedDuration() {
		return publishedDuration;
	}
	
	public void setPublishedDuration(Integer publishedDuration) {
		this.publishedDuration = publishedDuration;
	}

	public Restriction getRestriction() {
		return restriction;
	}
	
	public void setRestriction(Restriction restriction) {
		this.restriction = restriction;
	}

	@XmlElement(name = "threeD")
    public Boolean is3d() {
        return is3d;
    }

    public void set3d(Boolean is3d) {
        this.is3d = is3d;
    }
    
	protected void copyTo(Version destination) {
	    Preconditions.checkNotNull(destination);
	    
	    destination.setDuration(getDuration());
	    destination.setPublishedDuration(getPublishedDuration());
	    if (getRestriction() != null) {
	        destination.setRestriction(getRestriction().copy());
	    }
	    destination.set3d(is3d());
	    destination.setV4Aliases(getV4Aliases());
	}
}
