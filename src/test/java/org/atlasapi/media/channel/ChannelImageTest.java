package org.atlasapi.media.channel;

import static org.junit.Assert.*;

import org.atlasapi.media.entity.Image;
import org.atlasapi.media.entity.ImageColor;
import org.atlasapi.media.entity.ImageTheme;
import org.atlasapi.media.entity.ImageType;
import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableSet;


public class ChannelImageTest {

    /**
     * Tests that getImage() returns the current primary image on the channel, even if there are other current images 
     */
    @Test
    public void testReturnsCurrentNormalImageForGetImage() {
        LocalDate now = new LocalDate();
        
        TemporalField<Image> pastNormal = new TemporalField<Image>(createNormalImage("past normal"), now.minusYears(2), now.minusYears(1));
        TemporalField<Image> currentNormal = new TemporalField<Image>(createNormalImage("current normal"), now.minusYears(2), now.plusYears(1));
        TemporalField<Image> futureNormal = new TemporalField<Image>(createNormalImage("future normal"), now.plusYears(2), now.plusYears(3));
        TemporalField<Image> currentDt = new TemporalField<Image>(createDarkTransparentImage("current dt"), now.minusYears(2), now.plusYears(2));
        TemporalField<Image> currentLt = new TemporalField<Image>(createLightTransparentImage("current lt"), now.minusYears(2), now.plusYears(2));
        
        Channel channel = Channel.builder()
                .build();
        
        channel.setImages(ImmutableSet.of(pastNormal, currentNormal, futureNormal, currentDt, currentLt));
        
        assertEquals("current normal", channel.getImage().getCanonicalUri());
        
    }

    /**
     * Tests that getImage() returns the first future primary image on the channel if there is no current primary image, 
     * even if there are other current images 
     */
    @Test
    public void testReturnsFirstFutureNormalImageIfNoCurrentNormalImageForGetImage() {
        LocalDate now = new LocalDate();
        
        TemporalField<Image> pastNormal = new TemporalField<Image>(createNormalImage("past normal"), now.minusYears(2), now.minusYears(1));
        TemporalField<Image> firstFutureNormal = new TemporalField<Image>(createNormalImage("first future normal"), now.plusYears(1), now.plusYears(2));
        TemporalField<Image> secondFutureNormal = new TemporalField<Image>(createNormalImage("second future normal"), now.plusYears(2), now.plusYears(3));
        TemporalField<Image> currentDt = new TemporalField<Image>(createDarkTransparentImage("current dt"), now.minusYears(2), now.plusYears(2));
        TemporalField<Image> currentLt = new TemporalField<Image>(createLightTransparentImage("current lt"), now.minusYears(2), now.plusYears(2));
        
        Channel channel = Channel.builder()
                .build();
        
        channel.setImages(ImmutableSet.of(pastNormal, firstFutureNormal, secondFutureNormal, currentDt, currentLt));
        
        assertEquals("first future normal", channel.getImage().getCanonicalUri());
        
    }
    
    @Test
    public void testReturnsAllCurrentImagesForGetImages() {
        LocalDate now = new LocalDate();
        
        TemporalField<Image> pastNormal = new TemporalField<Image>(createNormalImage("past normal"), now.minusYears(2), now.minusYears(1));
        TemporalField<Image> currentNormal = new TemporalField<Image>(createNormalImage("current normal"), now.minusYears(2), now.plusYears(1));
        TemporalField<Image> futureNormal = new TemporalField<Image>(createNormalImage("future normal"), now.plusYears(2), now.plusYears(3));
        TemporalField<Image> currentDt = new TemporalField<Image>(createDarkTransparentImage("current dt"), now.minusYears(2), now.plusYears(2));
        TemporalField<Image> currentLt = new TemporalField<Image>(createLightTransparentImage("current lt"), now.minusYears(2), now.plusYears(2));

        Channel channel = Channel.builder()
                .build();
        
        channel.setImages(ImmutableSet.of(pastNormal, currentNormal, futureNormal, currentDt, currentLt));
        
        assertEquals(ImmutableSet.of(currentNormal.getValue(), currentDt.getValue(), currentLt.getValue()), channel.getImages());
        
    }

    private Image createNormalImage(String uri) {
        Image image = new Image(uri);
        
        image.setTheme(ImageTheme.LIGHT_OPAQUE);
        image.setType(ImageType.LOGO);
        image.setColor(ImageColor.COLOR);
        
        return image;
    }

    private Image createDarkTransparentImage(String uri) {
        Image image = new Image(uri);
        
        image.setTheme(ImageTheme.DARK_TRANSPARENT);
        image.setType(ImageType.LOGO);
        image.setColor(ImageColor.COLOR);
        
        return image;
    }

    private Image createLightTransparentImage(String uri) {
        Image image = new Image(uri);
        
        image.setTheme(ImageTheme.LIGHT_TRANSPARENT);
        image.setType(ImageType.LOGO);
        image.setColor(ImageColor.COLOR);
        
        return image;
    }
}
