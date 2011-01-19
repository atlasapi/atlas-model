package org.atlasapi.media.entity.simple;

public class Restriction {
	
	private Boolean restricted = null;

	private Integer minimumAge = null;

	private String message = null;

	public void setRestricted(Boolean rated) {
		this.restricted = rated;
	}

	public Boolean isResctriction() {
		return restricted;
	}

	public void setMinimumAge(Integer minimumAge) {
		this.minimumAge = minimumAge;
	}

	public Integer getMinimumAge() {
		return minimumAge;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
