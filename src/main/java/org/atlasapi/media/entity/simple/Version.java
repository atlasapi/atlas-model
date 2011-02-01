package org.atlasapi.media.entity.simple;

import com.google.common.base.Preconditions;


public abstract class Version {
	
	private Integer duration;
	private Integer publishedDuration;
	private Restriction restriction;

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
	
	protected void copyTo(Version destination) {
	    Preconditions.checkNotNull(destination);
	    
	    destination.setDuration(getDuration());
	    destination.setPublishedDuration(getPublishedDuration());
	    if (getRestriction() != null) {
	        destination.setRestriction(getRestriction().copy());
	    }
	}
}
