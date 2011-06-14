package org.atlasapi.persistence.content;

import org.atlasapi.media.entity.Person;

public interface PeopleListerListener {

	void personListed(Person person);
	
}
