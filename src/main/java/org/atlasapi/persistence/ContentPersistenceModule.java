package org.atlasapi.persistence;

import org.atlasapi.media.channel.ChannelGroupStore;
import org.atlasapi.media.channel.ChannelResolver;
import org.atlasapi.media.content.ContentResolver;
import org.atlasapi.media.content.ContentWriter;
import org.atlasapi.media.product.ProductResolver;
import org.atlasapi.media.product.ProductStore;
import org.atlasapi.media.segment.SegmentResolver;
import org.atlasapi.media.segment.SegmentWriter;
import org.atlasapi.media.topic.TopicQueryResolver;
import org.atlasapi.media.topic.TopicStore;
import org.atlasapi.persistence.content.people.ItemsPeopleWriter;
import org.atlasapi.persistence.shorturls.ShortUrlSaver;

public interface ContentPersistenceModule {

	ContentWriter contentWriter();
	
	ItemsPeopleWriter itemsPeopleWriter();
	
	ContentResolver contentResolver();
	
	TopicStore topicStore();
	
	TopicQueryResolver topicQueryResolver();

	ShortUrlSaver shortUrlSaver();
	
	SegmentWriter segmentWriter();
	
	SegmentResolver segmentResolver();
	
	ProductStore productStore();
	
	ProductResolver productResolver();

    ChannelGroupStore channelGroupStore();

    ChannelResolver channelResolver();
	
}
