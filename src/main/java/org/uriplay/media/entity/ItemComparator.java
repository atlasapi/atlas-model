package org.uriplay.media.entity;

import java.util.Comparator;

public class ItemComparator implements Comparator<Item> {

	@Override
	public int compare(Item o1, Item o2) {
		
		// Handle raw items
		if (!(o2 instanceof Episode)) {
			if (!(o1 instanceof Episode)) {
				return 0;
			}
			else {
				return -1;
			}
		}
		else if (!(o1 instanceof Episode)) {
			return 1;
		}
		
		Integer o1Series = ((Episode) o1).getSeriesNumber();
		Integer o2Series = ((Episode) o2).getSeriesNumber();
		Integer o1Episode = ((Episode) o1).getEpisodeNumber();
		Integer o2Episode = ((Episode) o2).getEpisodeNumber();

		// Handle null series number
		if (o2Series == null) {
			if (o1Series != null) {
				return -1;
			}
		}
		else if (o1Series == null) {
			return 1;
		}
		else {
			if (o1Series < o2Series) {
				return -1;
			}
			else if (o1Series > o2Series) {
				return 1;
			}
		}
		
		// Handle null episode number
		if (o2Episode == null) {
			if (o1Episode != null) {
				return -1;
			} else {
				// Everything is null
				return 0;
			}
		}
		else if (o1Episode == null) {
			return 1;
		}
		else {
			if (o1Episode < o2Episode) {
				return -1;
			}
			else if (o1Episode > o2Episode) {
				return 1;
			}
			else {
				return 0;
			}
		}
	}

}
