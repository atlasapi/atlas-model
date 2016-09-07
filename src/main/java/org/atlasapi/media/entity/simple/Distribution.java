package org.atlasapi.media.entity.simple;

import java.util.Objects;

import javax.annotation.Nullable;

import org.joda.time.DateTime;

public class Distribution {

    private String format;
    private DateTime releaseDate;
    private String distributor;

    public Distribution() {

    }

    private Distribution(Distribution.Builder builder) {
        this.format = builder.format;
        this.releaseDate = builder.releaseDate;
        this.distributor = builder.distributor;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Nullable
    public String getFormat() {
        return format;
    }

    @Nullable
    public DateTime getReleaseDate() {
        return releaseDate;
    }

    @Nullable
    public String getDistributor() {
        return distributor;
    }

    @Override
    public String toString() {
        return com.google.common.base.Objects.toStringHelper(this)
                .add("format", format)
                .add("releaseDate", releaseDate)
                .add("distributor", distributor)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Distribution)) {
            return false;
        }
        Distribution that = (Distribution) o;
        return Objects.equals(format, that.format) &&
                Objects.equals(releaseDate, that.releaseDate) &&
                Objects.equals(distributor, that.distributor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(format, releaseDate, distributor);
    }

    public Builder copy() {
        return new Builder()
                .withFormat(format)
                .withReleaseDate(releaseDate)
                .withDistributor(distributor);
    }

    public static class Builder {

        private String format;
        private DateTime releaseDate;
        private String distributor;

        private Builder() {

        }

        public Builder withFormat(@Nullable String format) {
            this.format = format;
            return this;
        }

        public Builder withReleaseDate(@Nullable DateTime releaseDate) {
            this.releaseDate = releaseDate;
            return this;
        }

        public Builder withDistributor(@Nullable String distributor) {
            this.distributor = distributor;
            return this;
        }

        public Distribution build() {
            return new Distribution(this);
        }
    }
}
