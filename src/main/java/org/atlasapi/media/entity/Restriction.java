package org.atlasapi.media.entity;

public class Restriction extends Identified {

	private Boolean restricted = null;

	private Integer minimumAge = null;

	private String message = null;

	public void setRestricted(Boolean rated) {
		this.restricted = rated;
	}

	public Boolean isRestricted() {
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
	
	public boolean hasRestrictionInformation() {
	    return restricted != null;
	}

	public static Restriction from() {
		Restriction restriction = new Restriction();
		restriction.setRestricted(false);
		return restriction;
	}

	public static Restriction from(String message) {
		if (message == null) {
			return from();
		}
		Restriction restriction = new Restriction();
		restriction.setRestricted(true);
		restriction.setMessage(message);
		return restriction;
	}

	public static Restriction from(int minimumAge) {
		Restriction restriction = new Restriction();
		restriction.setRestricted(true);
		restriction.setMinimumAge(minimumAge);
		return restriction;
	}

	public static Restriction from(int minimumAge, String message) {
		Restriction restriction = new Restriction();
		restriction.setRestricted(true);
		restriction.setMinimumAge(minimumAge);
		restriction.setMessage(message);
		return restriction;
	}
}
