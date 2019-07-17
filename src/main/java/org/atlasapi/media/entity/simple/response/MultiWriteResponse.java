package org.atlasapi.media.entity.simple.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class MultiWriteResponse {

    private final List<String> ids;

    @JsonCreator
    public MultiWriteResponse(
            @JsonProperty("ids") Iterable<String> ids
    ) {
        this.ids = ids == null ? ImmutableList.of() : ImmutableList.copyOf(ids);
    }

    public List<String> getIds() {
        return ids;
    }
}
