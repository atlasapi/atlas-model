package org.atlasapi.media.entity;

import java.util.List;

public class PriorityScoreReasons {

    private List<String> positive;
    private List<String> negative;

    public PriorityScoreReasons() {

    }

    public PriorityScoreReasons(List<String> positive, List<String> negative) {
        this.positive = positive;
        this.negative = negative;
    }

    public List<String> getPositive() {
        return positive;
    }

    public void setPositive(List<String> positive) {
        this.positive = positive;
    }

    public List<String> getNegative() {
        return negative;
    }

    public void setNegative(List<String> negative) {
        this.negative = negative;
    }
}