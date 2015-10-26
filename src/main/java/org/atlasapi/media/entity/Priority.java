package org.atlasapi.media.entity;

import com.google.common.base.Objects;

import java.util.List;

public class Priority {

    private Double score;
    private PriorityScoreReasons reasons;

    public Priority() {

    }

    public Priority(Double score, PriorityScoreReasons reasons) {
        this.score = score;
        this.reasons = reasons;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public PriorityScoreReasons getReasons() {
        return reasons;
    }

    public void setReasons(PriorityScoreReasons reasons) {
        this.reasons = reasons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priority priority = (Priority) o;
        return Objects.equal(score, priority.score) &&
            Objects.equal(reasons, priority.reasons);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(score, reasons);
    }
}
