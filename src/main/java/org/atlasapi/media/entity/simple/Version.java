package org.atlasapi.media.entity.simple;

import com.google.common.base.Preconditions;


public abstract class Version {
	
	private Integer duration;
	private Integer publishedDuration;
	private Restriction restriction;
	private Boolean is3d;

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
	}
}
