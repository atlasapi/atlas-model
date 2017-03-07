package org.atlasapi.media.entity;

import javax.annotation.Nullable;
import java.util.Arrays;

public enum ReviewType {
    NORMAL_REVIEW,
    SHORT_REVIEW,
    FOTD_REVIEW;

    public String toKey() {
        return this.name().toLowerCase();
    }

    public String authorTag() {
        return this.equals(NORMAL_REVIEW) ? "review_author" : this.toKey() + "_author";
    }

    public String authorInitialsTag() {
        return this.authorTag() + "_initials";
    }

    public String dateTag() {
        return this.equals(FOTD_REVIEW) ? "fotd_transmit_date" : "";
    }

    @Nullable
    public static ReviewType fromKey(String key) {

        return Arrays.stream(ReviewType.values())
                .filter(reviewType -> reviewType.name().equalsIgnoreCase(key))
                .findFirst().orElse(null);
    }
}
