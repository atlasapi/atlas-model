package org.atlasapi.persistence.logging;

public interface LogReader {

	Iterable<AdapterLogEntry> read();

	AdapterLogEntry requireById(String id);

}
