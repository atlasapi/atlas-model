package org.atlasapi.media.entity;

import java.util.List;

public class Priority {

    private Double score;
    private List<PriorityScoreReasons> reasons;

    public Priority(Double score, List<PriorityScoreReasons> reasons) {
        this.score = score;
        this.reasons = reasons;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<PriorityScoreReasons> getReasons() {
        return reasons;
    }

    public void setReasons(List<PriorityScoreReasons> reasons) {
        this.reasons = reasons;
    }
}
