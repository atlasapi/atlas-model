package org.atlasapi.media.entity.simple;

import java.util.List;

public class PriorityDetails {

    private Double score;
    private List<PriorityScoreReason> reasons;

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public List<PriorityScoreReason> getReasons() {
        return reasons;
    }

    public void setReasons(List<PriorityScoreReason> reasons) {
        this.reasons = reasons;
    }
}
