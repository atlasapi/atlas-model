package org.atlasapi.media.entity;

import com.google.common.base.Objects;

public class TopicRef {

	private Boolean supervised;
	private Float weighting;
	private Long topic;
	
	public TopicRef(Topic topic, Float weighting, Boolean supervised) {
		this.topic = topic.getId();
		this.weighting = weighting;
		this.supervised = supervised;
	}
	
	public TopicRef(Long topicId, Float weighting, Boolean supervised) {
		this.topic = topicId;
		this.weighting = weighting;
		this.supervised = supervised;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic.getId();
	}
	
	public void setTopicUri(Long topic) {
		this.topic = topic;
	}
	
	public void setWeighting(Float weighting) {
		this.weighting = weighting;
	}
	
	public void setSupervised(Boolean supervised) {
		this.supervised = supervised;
	}
	
	public Float getWeighting() {
		return weighting;
	}
	
	public Boolean isSupervised() {
		return supervised;
	}
	
	public Long getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(supervised, topic, weighting);
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
		    return true;
		}
		if (that instanceof TopicRef) {
		    TopicRef other = (TopicRef) that;
		    return Objects.equal(supervised, other.supervised) 
		            && Objects.equal(weighting, other.weighting) 
		            && Objects.equal(topic, other.topic);
		}
		return false;
	}
	
	@Override
	public String toString() {
	    return String.format("Ref topic %s, %+.2f, %s supervised", topic, weighting, supervised ? "" : "not");
	}
}
