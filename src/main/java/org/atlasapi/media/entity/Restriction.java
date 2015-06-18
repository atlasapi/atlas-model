package org.atlasapi.media.entity;

public class Restriction extends Identified {

	private Boolean restricted = null;

	private Integer minimumAge = null;

	private String message = null;

	private String authority = null;
    
    private String rating = null;
    
    public String getAuthority() {
        return authority;
    }
    
    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
    public String getRating() {
        return rating;
    }
    
    public void setRating(String rating) {
        this.rating = rating;
    }

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
	
	public static Restriction from(String authority, String rating) {
	    Restriction restriction = new Restriction();
	    restriction.setRestricted(true);
	    restriction.setAuthority(authority);
	    restriction.setRating(rating);
	    return restriction;
	}
	
	public Restriction copy() {
	    Restriction copy = new Restriction();
	    Identified.copyTo(this, copy);
	    copy.message = message;
	    copy.minimumAge = minimumAge;
	    copy.restricted = restricted;
	    return copy;
	}
}
