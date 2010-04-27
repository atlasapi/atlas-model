package org.uriplay.media.entity;

import java.util.Collections;
import java.util.List;

import com.google.soy.common.collect.Lists;

import junit.framework.TestCase;

public class ItemComparatorTest  extends TestCase {

	public void testOrderingList() {
		List<Item> items = Lists.newArrayList();
		
		Episode episodeX3 = new Episode();
		episodeX3.setEpisodeNumber(3);
		items.add(episodeX3);
		
		Episode episode12v1 = new Episode();
		episode12v1.setSeriesNumber(1);
		episode12v1.setEpisodeNumber(2);
		items.add(episode12v1);		

		Episode episode2X = new Episode();
		episode2X.setSeriesNumber(2);
		items.add(episode2X);
		
		Episode episode11 = new Episode();
		episode11.setSeriesNumber(1);
		episode11.setEpisodeNumber(1);
		items.add(episode11);

		Episode episode22 = new Episode();
		episode22.setSeriesNumber(2);
		episode22.setEpisodeNumber(2);
		items.add(episode22);

		Item item = new Item();
		items.add(item);
		
		Episode episode12v2 = new Episode();
		episode12v2.setSeriesNumber(1);
		episode12v2.setEpisodeNumber(2);
		items.add(episode12v2);
		
		Episode episodeX2 = new Episode();
		episodeX2.setEpisodeNumber(2);
		items.add(episodeX2);
		
		Episode episodeXX = new Episode();
		items.add(episodeXX);
		
		Collections.sort(items, new ItemComparator());
		
		assertEquals(items.get(0), episode11);
		assertEquals(items.get(1), episode12v1);
		assertEquals(items.get(2), episode12v2);
		assertEquals(items.get(3), episode22);
		assertEquals(items.get(4), episode2X);
		assertEquals(items.get(5), episodeX2);
		assertEquals(items.get(6), episodeX3);
		assertEquals(items.get(7), episodeXX);
		assertEquals(items.get(8), item);
	}
}
