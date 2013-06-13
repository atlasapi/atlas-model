package org.atlasapi.media.channel;

import static org.junit.Assert.*;

import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class TemporalFieldTest {

    @Test
    public void testFetchCurrentTitle() {
        LocalDate now = new LocalDate();
        
        TemporalField<String> past = new TemporalField<String>("past", now.minusYears(3), now.minusYears(1));
        TemporalField<String> current = new TemporalField<String>("current", now.minusYears(1), now.plusYears(1));
        TemporalField<String> future = new TemporalField<String>("future", now.plusYears(1), now.plusYears(3));
        
        String currentValue = TemporalField.currentOrFutureValue(ImmutableList.of(past, current, future));
        
        assertEquals("current", currentValue);
    }

    /**
     * if there is no current title, then currentValue() should return the next future value
     */
    @Test
    public void testFetchCurrentTitleIfNoCurrent() {
        LocalDate now = new LocalDate();
        
        TemporalField<String> past = new TemporalField<String>("past", now.minusYears(3), now.minusYears(1));
        TemporalField<String> firstFuture = new TemporalField<String>("first future", now.plusYears(1), now.plusYears(2));
        TemporalField<String> secondFuture = new TemporalField<String>("second future", now.plusYears(2), now.plusYears(3));
        
        String futureValue = TemporalField.currentOrFutureValue(ImmutableList.of(past, firstFuture, secondFuture));
        
        assertEquals("first future", futureValue);
    }
    
    @Test
    public void testFetchCurrentTitles() {
        LocalDate now = new LocalDate();
        
        TemporalField<String> past = new TemporalField<String>("past", now.minusYears(3), now.minusYears(1));
        TemporalField<String> firstNow = new TemporalField<String>("first now", now.minusYears(2), now.plusYears(1));
        TemporalField<String> secondNow = new TemporalField<String>("second now", now.minusYears(1), now.plusYears(3));
        
        Set<String> currentValues = TemporalField.currentValues(ImmutableList.of(past, firstNow, secondNow));
        
        assertEquals(ImmutableSet.of("first now", "second now"), currentValues);
    }
    
    @Test
    public void testCurrentValuesReturnsEmptySetIfNoCurrent() {
        LocalDate now = new LocalDate();
        
        TemporalField<String> past = new TemporalField<String>("past", now.minusYears(3), now.minusYears(1));
        TemporalField<String> firstFuture = new TemporalField<String>("first future", now.plusYears(1), now.plusYears(2));
        TemporalField<String> secondFuture = new TemporalField<String>("second future", now.plusYears(2), now.plusYears(3));
        
        Set<String> currentValues = TemporalField.currentValues(ImmutableList.of(past, firstFuture, secondFuture));
        
        assertTrue(currentValues.isEmpty());
    }

    @Test
    public void testFetchTitleForPastDate() {
        LocalDate pastDate = (new LocalDate()).minusYears(1);
        
        TemporalField<String> before = new TemporalField<String>("before", pastDate.minusYears(2), pastDate.minusYears(1));
        TemporalField<String> onPastDate = new TemporalField<String>("on date", pastDate.minusYears(1), pastDate.plusYears(1));
        TemporalField<String> after = new TemporalField<String>("after", pastDate.plusYears(1), pastDate.plusYears(2));
        
        Set<String> valueForDate = TemporalField.valuesForDate(ImmutableList.of(before, onPastDate, after), pastDate);
        
        assertEquals("on date", Iterables.getOnlyElement(valueForDate));
        
        valueForDate = TemporalField.valuesForDate(ImmutableList.of(after), pastDate);
        
        assertTrue(valueForDate.isEmpty());
    }

    @Test
    public void testFetchPastTitleNoMatch() {
        LocalDate pastDate = (new LocalDate()).minusYears(1);
        
        TemporalField<String> before = new TemporalField<String>("before", pastDate.minusYears(3), pastDate.minusYears(1));
        TemporalField<String> onPastDate = new TemporalField<String>("on date", pastDate.plusYears(1), pastDate.plusYears(2));
        TemporalField<String> after = new TemporalField<String>("after", pastDate.plusYears(2), pastDate.plusYears(3));
        
        Set<String> valueForDate = TemporalField.valuesForDate(ImmutableList.of(before, onPastDate, after), pastDate);
        
        assertTrue(valueForDate.isEmpty());
    }
}
