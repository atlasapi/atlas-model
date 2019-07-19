package org.atlasapi.media.entity.simple.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExplicitEquivalenceResponse {

    private final List<EquivalenceLink> added;
    private final List<EquivalenceLink> removed;

    @JsonCreator
    public ExplicitEquivalenceResponse(
            @JsonProperty("added") Iterable<EquivalenceLink> added,
            @JsonProperty("removed") Iterable<EquivalenceLink> removed
    ) {
        this.added = added == null ? ImmutableList.of() : ImmutableList.copyOf(added);
        this.removed = removed == null ? ImmutableList.of() : ImmutableList.copyOf(removed);
    }

    public List<EquivalenceLink> getAdded() {
        return added;
    }

    public List<EquivalenceLink> getRemoved() {
        return removed;
    }

    public static class EquivalenceLink {
        private final String from;
        private final String to;

        public EquivalenceLink(@JsonProperty("from") String from, @JsonProperty("to") String to) {
            this.from = checkNotNull(from);
            this.to = checkNotNull(to);
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }
}
