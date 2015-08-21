package org.atlasapi.media.entity;

import com.google.common.base.Objects;

public class PriorityScoreReasons {

    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public PriorityScoreReasons(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriorityScoreReasons that = (PriorityScoreReasons) o;
        return Objects.equal(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(reason);
    }
}
