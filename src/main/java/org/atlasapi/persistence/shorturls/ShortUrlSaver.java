package org.atlasapi.persistence.shorturls;

import org.atlasapi.media.content.Identified;

public interface ShortUrlSaver {

	void save(String shortUrl, Identified mapsTo);

}
