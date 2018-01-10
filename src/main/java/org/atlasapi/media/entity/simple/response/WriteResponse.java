package org.atlasapi.media.entity.simple.response;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WriteResponse {

    private final String id;

    @JsonCreator
    public WriteResponse(
            @JsonProperty("id") String id
    ) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
