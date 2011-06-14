package org.atlasapi.persistence.content;

public interface PeopleLister {

	void list(PeopleListerListener handler);
	
}
