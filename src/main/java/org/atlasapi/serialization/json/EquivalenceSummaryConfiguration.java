package org.atlasapi.serialization.json;

import java.util.List;
import java.util.Map;

import org.atlasapi.equiv.ContentRef;
import org.atlasapi.media.entity.Publisher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EquivalenceSummaryConfiguration {

    @JsonCreator
    public EquivalenceSummaryConfiguration(
            @JsonProperty("subject") String subject,
            @JsonProperty("parent") String parent,
            @JsonProperty("candidates") List<String> candidates,
            @JsonProperty("equivalents") Map<Publisher, ContentRef> equivalents
    ) {}
    
}
