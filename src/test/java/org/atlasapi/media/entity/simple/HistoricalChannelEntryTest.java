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
        
        HistoricalChannelEntry entry1 = new HistoricalChannelEntry();
        entry1.setImage("image1");
        entry1.setImages(ImmutableList.of(createImage("image1")));
        entry1.setStartDate(date);
        
        HistoricalChannelEntry entry2 = new HistoricalChannelEntry();
        entry2.setImage("image2");
        entry2.setImages(ImmutableList.of(createImage("image2"), createImage("image3")));
        entry2.setStartDate(date);
        
        assertTrue(entry1.equals(entry2));
        
        entry2.setImage("image1");
        entry2.setImages(entry1.getImages());
        entry2.setStartDate(date.plusDays(1));
        
        assertFalse(entry1.equals(entry2));
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
