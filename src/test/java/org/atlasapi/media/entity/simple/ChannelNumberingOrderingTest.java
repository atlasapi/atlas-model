package org.atlasapi.media.entity.simple;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

public class ChannelNumberingOrderingTest {
    
    private static final Ordering<ChannelNumbering> ordering = new ChannelNumberingOrdering();
    
    @Test
    public void testNullNumberLast() {
        ChannelNumbering nullNumber = new ChannelNumbering();
        ChannelNumbering nonNullNumber = new ChannelNumbering();
        nonNullNumber.setChannelNumber("1");
        
        for (List<ChannelNumbering> unsorted : Collections2.permutations(ImmutableList.of(nullNumber, nonNullNumber))) {
        ImmutableList<ChannelNumbering> sortedCopy = ordering.immutableSortedCopy(unsorted);
        assertEquals(nonNullNumber, sortedCopy.get(0));
        assertEquals(nullNumber, sortedCopy.get(1));
        }
    }

    @Test
    public void testZeroPrefixedNumberLast() {
        ChannelNumbering zeroPrefixed = new ChannelNumbering();
        zeroPrefixed.setChannelNumber("01");
        ChannelNumbering nonZeroPrefixed = new ChannelNumbering();
        nonZeroPrefixed.setChannelNumber("1");
        
        for (List<ChannelNumbering> unsorted : Collections2.permutations(ImmutableList.of(zeroPrefixed, nonZeroPrefixed))) {
            ImmutableList<ChannelNumbering> sortedCopy = ordering.immutableSortedCopy(unsorted);
            assertEquals(nonZeroPrefixed, sortedCopy.get(0));
            assertEquals(zeroPrefixed, sortedCopy.get(1));
        }
    }
    
    @Test
    public void testNonZeroPrefixedNumberOrdering() {
        ChannelNumbering one = new ChannelNumbering();
        one.setChannelNumber("1");
        ChannelNumbering two = new ChannelNumbering();
        two.setChannelNumber("2");
        
        for (List<ChannelNumbering> unsorted : Collections2.permutations(ImmutableList.of(one, two))) {
            ImmutableList<ChannelNumbering> sortedCopy = ordering.immutableSortedCopy(unsorted);
            assertEquals(one, sortedCopy.get(0));
            assertEquals(two, sortedCopy.get(1));
        }
    }
    
    @Test
    public void testZeroPrefixedNumberOrdering() {
        ChannelNumbering one = new ChannelNumbering();
        one.setChannelNumber("01");
        ChannelNumbering two = new ChannelNumbering();
        two.setChannelNumber("02");
        
        for (List<ChannelNumbering> unsorted : Collections2.permutations(ImmutableList.of(one, two))) {
            ImmutableList<ChannelNumbering> sortedCopy = ordering.immutableSortedCopy(unsorted);
            assertEquals(one, sortedCopy.get(0));
            assertEquals(two, sortedCopy.get(1));
        }
    }
    
    @Test
    public void testMixedNumberOrdering() {
        ChannelNumbering one = new ChannelNumbering();
        one.setChannelNumber("1");
        ChannelNumbering two = new ChannelNumbering();
        two.setChannelNumber("2");
        ChannelNumbering zeroOne = new ChannelNumbering();
        zeroOne.setChannelNumber("01");
        ChannelNumbering zeroTwo = new ChannelNumbering();
        zeroTwo.setChannelNumber("02");
        
        for (List<ChannelNumbering> unsorted : Collections2.permutations(ImmutableList.of(one, two, zeroOne, zeroTwo))) {
            ImmutableList<ChannelNumbering> sortedCopy = ordering.immutableSortedCopy(unsorted);
            assertEquals(one, sortedCopy.get(0));
            assertEquals(two, sortedCopy.get(1));
            assertEquals(zeroOne, sortedCopy.get(2));
            assertEquals(zeroTwo, sortedCopy.get(3));
        }
    }
}
