package org.atlasapi.media.entity.simple;

import javax.annotation.Nullable;

import com.google.common.collect.Ordering;

public class ChannelNumberingOrdering extends Ordering<ChannelNumbering> {

    @Override
    public int compare(@Nullable ChannelNumbering left, @Nullable ChannelNumbering right) {
        String leftNumber = left.getChannelNumber();
        String rightNumber = right.getChannelNumber();
        
        if (leftNumber != null) {
            if (rightNumber != null) {
                return compareChannelNumbers(leftNumber, rightNumber);
            }
            return -1;
        }
        if (rightNumber != null) {
            return 1;
        }
        return 0;
    }
        
     private int compareChannelNumbers(String left, String right) {   
        if (left.startsWith("0")) {
            if (right.startsWith("0")) {
                return Double.compare(Integer.parseInt(left), Integer.parseInt(right));
            }
            return 1;
        }
        if (right.startsWith("0")) {
            return -1;
        }
        return Double.compare(Integer.parseInt(left), Integer.parseInt(right));
    }

}
