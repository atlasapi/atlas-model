package org.atlasapi.media.entity.simple;


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

}
