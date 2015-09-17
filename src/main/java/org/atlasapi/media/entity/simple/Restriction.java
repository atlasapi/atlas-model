package org.atlasapi.media.entity.simple;

public class Restriction {
	
	private Boolean restricted = null;

	private Integer minimumAge = null;

	private String message = null;

	private String authority = null;

	private String rating = null;

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

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getRating() {
		return rating;
	}

	public Restriction copy() {
	    Restriction copy = new Restriction();
	    
	    copy.setRestricted(isRestricted());
	    copy.setMinimumAge(getMinimumAge());
	    copy.setMessage(getMessage());
		copy.setRating(getRating());
		copy.setAuthority(getAuthority());
	    
	    return copy;
	}
}
