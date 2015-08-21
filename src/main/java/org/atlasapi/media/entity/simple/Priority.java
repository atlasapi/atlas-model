package org.atlasapi.media.entity.simple;

public class Priority {

    private Double score;
    private PriorityScoreReason reasons;

    public Priority(Double score, PriorityScoreReason reasons) {

        this.score = score;
        this.reasons = reasons;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public PriorityScoreReason getReasons() {
        return reasons;
    }

    public void setReasons(PriorityScoreReason reasons) {
        this.reasons = reasons;
    }
}
