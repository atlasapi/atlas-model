package org.atlasapi.media.util;

import org.atlasapi.media.entity.Broadcast;
import org.atlasapi.media.entity.Item;

import com.google.common.base.Objects;
import com.metabroadcast.common.base.Maybe;

public class ItemAndBroadcast {
	
	private final Item item;
	private final Maybe<Broadcast> broadcast;
	
	public ItemAndBroadcast(Item item, Maybe<Broadcast> broadcast) {
		this.item = item;
		this.broadcast = broadcast;
	}
	
	@Override 
	public int hashCode() {
		return Objects.hashCode(item, broadcast);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ItemAndBroadcast) {
			ItemAndBroadcast other = (ItemAndBroadcast) obj;
			return item.equals(other.item) && broadcast.equals(other.broadcast);
		}
		return false;
	}
	
	public Maybe<Broadcast> getBroadcast() {
		return broadcast;
	}
	
	public Item getItem() {
		return item;
	}
}