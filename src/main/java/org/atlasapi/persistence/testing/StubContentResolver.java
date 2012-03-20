package org.atlasapi.persistence.testing;

import java.util.Map;

import org.atlasapi.media.entity.Content;
import org.atlasapi.persistence.content.ContentResolver;
import org.atlasapi.persistence.content.ResolvedContent;
import org.atlasapi.persistence.content.ResolvedContent.ResolvedContentBuilder;

import com.google.common.collect.Maps;

public class StubContentResolver implements ContentResolver {

	public static ContentResolver RESOLVES_NOTHING = new StubContentResolver();
	
	private Map<String, Content> data = Maps.newHashMap();
	
	public StubContentResolver respondTo(Content content) {
		data.put(content.getCanonicalUri(), content);
		return this;
	}

	@Override
	public ResolvedContent findByCanonicalUris(Iterable<String> uris) {
		ResolvedContentBuilder builder = ResolvedContent.builder();
		for (String uri : uris) {
			builder.put(uri, data.get(uri));
		}
		return builder.build();
	}
}

