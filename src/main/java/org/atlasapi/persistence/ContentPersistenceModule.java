package org.atlasapi.persistence;

import org.atlasapi.media.channel.ChannelGroupStore;
import org.atlasapi.media.channel.ChannelResolver;
import org.atlasapi.media.product.ProductResolver;
import org.atlasapi.media.product.ProductStore;
import org.atlasapi.media.segment.SegmentResolver;
import org.atlasapi.media.segment.SegmentWriter;
import org.atlasapi.persistence.content.ContentResolver;
import org.atlasapi.persistence.content.ContentWriter;
import org.atlasapi.persistence.content.people.ItemsPeopleWriter;
import org.atlasapi.persistence.shorturls.ShortUrlSaver;
import org.atlasapi.persistence.topic.TopicQueryResolver;
import org.atlasapi.persistence.topic.TopicStore;

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
