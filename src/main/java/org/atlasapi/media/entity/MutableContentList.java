package org.atlasapi.media.entity;

import java.util.List;

public interface MutableContentList<T extends Content> {

	List<T> getContents();
	
	void setContents(Iterable<? extends T> contents);

	void addContents(Iterable<? extends T> contents);
	
}
