package org.atlasapi.media.entity;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.base.Function;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

public final class Schedule {

	private final Map<String, List<Item>> channelMap;

	public List<Item> getItemsFromOnlyChannel() {
		if (channelMap.size() != 1) {
			throw new IllegalArgumentException("Mutiple channels found");
		}
		return Iterables.getOnlyElement(channelMap.values());
	}

	public static Schedule fromItems(Iterable<? extends Item> items) {
		HashMultimap<String, ItemAndBroadcast> map = HashMultimap.create();
		for (Item item : items) {
			for (Version version : item.getVersions()) {
				for (Broadcast broadcast : version.getBroadcasts()) {
					map.put(broadcast.getBroadcastOn(), new ItemAndBroadcast(item, broadcast));
				}
			}
		}
		Map<String, List<Item>> mmap = Maps.newHashMap();
		for (Entry<String, Collection<ItemAndBroadcast>> entry : map.asMap().entrySet()) {
			ImmutableList<ItemAndBroadcast> sorted = Ordering.natural().immutableSortedCopy(entry.getValue());
			mmap.put(entry.getKey(), Lists.transform(sorted, TO_ITEM));
		}
		return new Schedule(mmap);
	}
	
	private static final class ItemAndBroadcast implements Comparable<ItemAndBroadcast> {
		
		private final Item item;
		private final Broadcast broadcast;
		
		public ItemAndBroadcast(Item item, Broadcast broadcast) {
			this.item = item;
			this.broadcast = broadcast;
		}

		@Override
		public int compareTo(ItemAndBroadcast other) {
			return broadcast.getTransmissionTime().compareTo(other.broadcast.getTransmissionTime());
		}
		
	}
	
	private static Function<ItemAndBroadcast, Item> TO_ITEM =  new Function<ItemAndBroadcast, Item>() {
		@Override
		public Item apply(ItemAndBroadcast input) {
			return input.item;
		}
	};
	
	private Schedule(Map<String, List<Item>> channelMap) {
		this.channelMap = channelMap;
	}
	
	@Override
	public int hashCode() {
		return channelMap.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Schedule) {
			return channelMap.equals(((Schedule) obj).channelMap);
		}
		return false;
	}
}
