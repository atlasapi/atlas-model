package org.atlasapi.media.channel;

import java.util.Map;

import org.atlasapi.media.common.Id;

import com.metabroadcast.common.base.Maybe;

public interface ChannelResolver extends ChannelLister {

	@Deprecated
	Maybe<Channel> fromKey(String key);

	Maybe<Channel> fromId(Id id);
	
	Maybe<Channel> fromUri(String uri);
	
	Iterable<Channel> forIds(Iterable<Id> ids);
		
	/**
	 * Return a map whose keys are aliases prefixed with aliasPrefix
	 * 
	 * @param aliasPrefix
	 * @return
	 */
	Map<String, Channel> forAliases(String aliasPrefix);
	
}
