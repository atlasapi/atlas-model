package org.atlasapi.media.content.item;

import static com.google.common.base.Preconditions.checkNotNull;

public final class Restriction {

	private static final Restriction UNRESTRICTED = new Restriction(false, null, null);

    public final static Restriction unrestricted() {
        return UNRESTRICTED;
    }

    public final static Restriction restrictedWithMessage(String message) {
        return new Restriction(true, null, checkNotNull(message));
    }
    
    public final static Restriction unrestrictedWithMessage(String message) {
        return new Restriction(false, null, checkNotNull(message));
    }

    public final static Restriction restrictedWithMinimumAge(int minimumAge) {
        return new Restriction(true, minimumAge, null);
    }
	
    private final Boolean restricted;
	private final Integer minimumAge;
	private final String message;

	public Restriction(Boolean restricted, Integer minimumAge, String message) {
        this.restricted = restricted;
        this.minimumAge = minimumAge;
        this.message = message;
    }
	
	public Boolean isRestricted() {
		return restricted;
	}

	public Integer minimumAge() {
		return minimumAge;
	}

	public String message() {
		return message;
	}
	
	public boolean hasRestrictionInformation() {
	    return message != null;
	}

	public Restriction copyWithRestricted(Boolean restricted) {
	    return new Restriction(restricted, this.minimumAge, this.message);
	}
	
	public Restriction copyWithMinimumAge(Integer age) {
	    return new Restriction(this.restricted, age, this.message);
	}
	
	public Restriction copyWithMessage(String message) {
	    return new Restriction(this.restricted, this.minimumAge, message);
	}
}
