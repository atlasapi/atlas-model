package org.atlasapi.media.entity.simple;

import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

@XmlType(name="topicref", namespace=PLAY_SIMPLE_XML.NS)
public class TopicRef {

	private float weighting;
	private boolean supervised;
	private Topic topic;
    private String relationship;
    private PublisherDetails publisher;
    private Integer offset;
	
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

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setPublisher(PublisherDetails publisher) {
        this.publisher = publisher;
    }

    public PublisherDetails getPublisher() {
        return publisher;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
