package org.atlasapi.media.entity;

import com.google.common.base.Objects;

public class TopicRef {

	private boolean supervised;
	private float weighting;
	private String topic;
	
	public TopicRef(Topic topic, float weighting, boolean supervised) {
		this.topic = Identified.TO_URI.apply(topic);
		this.weighting = weighting;
		this.supervised = supervised;
	}
	
	public TopicRef(String topicUri, float weighting, boolean supervised) {
		this.topic = topicUri;
		this.weighting = weighting;
		this.supervised = supervised;
	}
	
	public void setTopic(Topic topic) {
		this.topic = Identified.TO_URI.apply(topic);
	}
	
	public void setTopicUri(String topic) {
		this.topic = topic;
	}
	
	public void setWeighting(float weighting) {
		this.weighting = weighting;
	}
	
	public void setSupervised(boolean supervised) {
		this.supervised = supervised;
	}
	
	public float getWeighting() {
		return weighting;
	}
	
	public boolean isSupervised() {
		return supervised;
	}
	
	public String getTopic() {
		return topic;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(supervised, topic, weighting);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicRef other = (TopicRef) obj;
		return Objects.equal(supervised, other.supervised) 
				&& Objects.equal(weighting, other.weighting) 
				&& Objects.equal(topic, other.topic);
	}
	
	
}
