package org.atlasapi.application;

import java.util.List;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableList;

public class ApplicationSources {

    private final boolean precedence;
    private final List<SourceReadEntry> reads;
    private final List<Publisher> writes;

    private ApplicationSources(Builder builder) {
        this.precedence = builder.precedence;
        this.reads = ImmutableList.copyOf(builder.reads);
        this.writes = ImmutableList.copyOf(builder.writes);
    }

    public boolean isPrecedenceEnabled() {
        return precedence;
    }

    public List<SourceReadEntry> getReads() {
        return reads;
    }

    public List<Publisher> getWrites() {
        return writes;
    }

    public Builder copy() {
        return builder()
                .withPrecedence(this.isPrecedenceEnabled())
                .withReads(this.getReads())
                .withWrites(this.getWrites());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public boolean precedence;
        private List<SourceReadEntry> reads;
        private List<Publisher> writes;

        public Builder withPrecedence(boolean precedence) {
            this.precedence = precedence;
            return this;
        }

        public Builder withReads(List<SourceReadEntry> reads) {
            this.reads = reads;
            return this;
        }

        public Builder withWrites(List<Publisher> writes) {
            this.writes = writes;
            return this;
        }

        public ApplicationSources build() {
            return new ApplicationSources(this);
        }
    }

}
