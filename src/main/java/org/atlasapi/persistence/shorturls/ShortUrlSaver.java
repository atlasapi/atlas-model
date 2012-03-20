package org.atlasapi.persistence.shorturls;

import org.atlasapi.media.entity.Identified;

public interface ShortUrlSaver {

	void save(String shortUrl, Identified mapsTo);

}
