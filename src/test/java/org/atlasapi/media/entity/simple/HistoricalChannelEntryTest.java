package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableList;


public class HistoricalChannelEntryTest {

    @Test
    public void testEqualityOnDate() {
        LocalDate date = new LocalDate();
        
        HistoricalChannelEntry entry1 = new HistoricalChannelEntry(date);
        entry1.setImage("image1");
        entry1.setImages(ImmutableList.of(createImage("image1")));
        
        HistoricalChannelEntry entry2 = new HistoricalChannelEntry(date);
        entry2.setImage("image2");
        entry2.setImages(ImmutableList.of(createImage("image2"), createImage("image3")));
        
        assertTrue(entry1.equals(entry2));
        
        HistoricalChannelEntry entry3 = new HistoricalChannelEntry(date.plusDays(1));
        entry3.setImage("image1");
        entry3.setImages(entry1.getImages());
        
        assertFalse(entry1.equals(entry3));
    }

    private Image createImage(String uri) {
        Image image = new Image();
        
        image.setUri(uri);
        image.setTheme("dark_transparent");
        image.setColor("color");
        image.setType("logo");
        
        return image;
    }

}
