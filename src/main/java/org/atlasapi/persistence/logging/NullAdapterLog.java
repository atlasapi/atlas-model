package org.atlasapi.persistence.logging;

public class NullAdapterLog implements AdapterLog {

	@Override
	public void record(AdapterLogEntry entry) {
		// do nothing
	}

}
