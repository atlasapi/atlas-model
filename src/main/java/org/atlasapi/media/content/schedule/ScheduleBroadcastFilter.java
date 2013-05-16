package org.atlasapi.media.content.schedule;

import org.joda.time.Duration;
import org.joda.time.Interval;

import com.google.common.base.Predicate;

/**
 * Predicate for filtering broadcast intervals in a schedule interval. Valid
 * broadcast intervals either entirely or partially overlap with the schedule
 * interval or, if the schedule interval is an instance, abut its end.
 * 
 * For a non-empty schedule interval the following cases are
 * covered:
 * 
 * <pre>
 * Schedule Interval:          SS-------------------SE 
 * Totally Contained:                bs------be 
 * Only Start Contained:                        bs-------be 
 * Only End Contained:     bs------be 
 * Exact match:                bs-------------------be 
 * Entirely Overlapping:   bs----------------------------be
 * </pre>
 * 
 * For an empty interval the following cases are covered:
 * <pre>
 * Schedule Interval:          |
 * Contains:              bs--------be
 * Abuts end:                  bs------be
 * </pre>
 * 
 * @author Fred van den Driessche (fred@metabroadcast.com)
 * 
 */
public abstract class ScheduleBroadcastFilter implements Predicate<Interval> {

    public static final ScheduleBroadcastFilter valueOf(Interval scheduleInterval) {
        if (Duration.ZERO.equals(scheduleInterval.toDuration())) {
            return new EmptyScheduleBroadcastFilter(scheduleInterval);
        }
        return new RegularScheduleBroadcastFilter(scheduleInterval);
    }
    
    private static class EmptyScheduleBroadcastFilter extends ScheduleBroadcastFilter {
        
        private Interval scheduleInterval;

        public EmptyScheduleBroadcastFilter(Interval scheduleInterval) {
            this.scheduleInterval = scheduleInterval;
        }

        @Override
        public boolean apply(Interval broadcastInterval) {
            return !broadcastInterval.getStart().isAfter(scheduleInterval.getStart())
                && broadcastInterval.getEnd().isAfter(scheduleInterval.getEnd());
        }
        
    }

    private static class RegularScheduleBroadcastFilter extends ScheduleBroadcastFilter {

        private final Interval scheduleInterval;

        private RegularScheduleBroadcastFilter(Interval scheduleInterval) {
            this.scheduleInterval = scheduleInterval;
        }

        @Override
        public boolean apply(Interval broadcastInterval) {
            return scheduleInterval.overlaps(broadcastInterval);
        }

    }

}