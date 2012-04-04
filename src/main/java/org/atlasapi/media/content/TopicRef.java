package org.atlasapi.media.content;

import static com.google.common.base.Preconditions.checkNotNull;

import org.atlasapi.media.topic.Topic;
import org.atlasapi.media.topic.Topic.TopicIdentifier;

import com.google.common.base.Objects;

public class TopicRef {

	private final Boolean supervised;
	private final Float weighting;
	private final TopicIdentifier topicId;
	
	public TopicRef(Topic topic, Float weighting, Boolean supervised) {
	    this(topic.toIdentifier(), weighting, supervised);
	}
	
	public TopicRef(TopicIdentifier topicIdentifier, Float weighting, Boolean supervised) {
		this.topicId = checkNotNull(topicIdentifier, "Can't create ref for null topic ID");
		this.weighting = weighting;
		this.supervised = supervised;
	}
	
	public Float weighting() {
		return weighting;
	}
	
	public Boolean supervised() {
		return supervised;
	}
	
	public TopicIdentifier getTopic() {
		return topicId;
	}

	@Override
	public int hashCode() {
		return topicId.hashCode();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
		    return true;
		}
		if (that instanceof TopicRef) {
		    TopicRef other = (TopicRef) that;
		    return Objects.equal(topicId, other.topicId);
		}
		return false;
	}
	
	@Override
	public String toString() {
	    return String.format("Ref topic %s", topicId);
	}
}
