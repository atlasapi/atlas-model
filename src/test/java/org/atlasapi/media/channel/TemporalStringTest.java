package org.atlasapi.media.channel;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class TemporalStringTest {

    @Test
    public void testFetchCurrentTitle() {
        LocalDate now = new LocalDate();
        
        TemporalString past = new TemporalString("past", now.minusYears(3), now.minusYears(1));
        TemporalString current = new TemporalString("current", now.minusYears(1), now.plusYears(1));
        TemporalString future = new TemporalString("future", now.plusYears(1), now.plusYears(3));
        
        String currentValue = TemporalString.currentValue(ImmutableList.of(past, current, future));
        
        assertEquals("current", currentValue);
    }

    /**
     * if there is no current title, then currentValue() should return the next future value
     */
    @Test
    public void testFetchCurrentTitleIfNoCurrent() {
        LocalDate now = new LocalDate();
        
        TemporalString past = new TemporalString("past", now.minusYears(3), now.minusYears(1));
        TemporalString firstFuture = new TemporalString("first future", now.plusYears(1), now.plusYears(2));
        TemporalString secondFuture = new TemporalString("second future", now.plusYears(2), now.plusYears(3));
        
        String futureValue = TemporalString.currentValue(ImmutableList.of(past, firstFuture, secondFuture));
        
        assertEquals("first future", futureValue);
    }

    @Test
    public void testFetchTitleForPastDate() {
        LocalDate pastDate = (new LocalDate()).minusYears(1);
        
        TemporalString before = new TemporalString("before", pastDate.minusYears(2), pastDate.minusYears(1));
        TemporalString onPastDate = new TemporalString("on date", pastDate.minusYears(1), pastDate.plusYears(1));
        TemporalString after = new TemporalString("after", pastDate.plusYears(1), pastDate.plusYears(2));
        
        String valueForDate = TemporalString.valueForDate(ImmutableList.of(before, onPastDate, after), pastDate);
        
        assertEquals("on date", valueForDate);
        
        // if no value for a given date, return null
        valueForDate = TemporalString.valueForDate(ImmutableList.of(after), pastDate);
        
        assertEquals(null, valueForDate);
    }

    @Test
    public void testFetchPastTitleNoMatch() {
        LocalDate pastDate = (new LocalDate()).minusYears(1);
        
        TemporalString before = new TemporalString("before", pastDate.minusYears(3), pastDate.minusYears(1));
        TemporalString onPastDate = new TemporalString("on date", pastDate.plusYears(1), pastDate.plusYears(2));
        TemporalString after = new TemporalString("after", pastDate.plusYears(2), pastDate.plusYears(3));
        
        String valueForDate = TemporalString.valueForDate(ImmutableList.of(before, onPastDate, after), pastDate);
        
        assertEquals(null, valueForDate);
    }
}
