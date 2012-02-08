package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlType(name="topicref", namespace=PLAY_SIMPLE_XML.NS)
public class TopicRef {

	private float weighting;
	private boolean supervised;
	private Topic topic;
	
	public float getWeighting() {
		return weighting;
	}
	
	public void setWeighting(float weighting) {
		this.weighting = weighting;
	}
	
	public boolean isSupervised() {
		return supervised;
	}
	
	public void setSupervised(boolean supervised) {
		this.supervised = supervised;
	}
	
	public Topic getTopic() {
		return topic;
	}
	
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
}
