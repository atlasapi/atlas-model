package org.atlasapi.persistence.content.schedule;

import org.atlasapi.media.channel.Channel;
import org.atlasapi.media.content.Item;
import org.atlasapi.media.content.Publisher;
import org.atlasapi.persistence.content.schedule.ScheduleEntry.ItemRefAndBroadcast;

public interface ScheduleWriter {

    @Deprecated
    void writeScheduleFor(Iterable<? extends Item> items);

    void writeCompleteEntry(ScheduleEntry entry);

	void replaceScheduleBlock(Publisher publisher, Channel channel,
			Iterable<ItemRefAndBroadcast> itemsAndBroadcasts);

}
