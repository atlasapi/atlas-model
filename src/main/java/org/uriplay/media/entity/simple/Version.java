package org.uriplay.media.entity.simple;


public abstract class Version {
	
	private Integer duration;
	private Integer publishedDuration;
	private String rating;
	private String ratingText;
	
	public void setPublishedDuration(Integer publishedDuration) {
		this.publishedDuration = publishedDuration;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getRatingText() {
		return ratingText;
	}
	
	public void setRatingText(String ratingText) {
		this.ratingText = ratingText;
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

}
