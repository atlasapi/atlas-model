package org.atlasapi.serialization.json.configuration.model;

import java.util.Set;

import org.atlasapi.media.entity.LookupRef;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class LookupEntryConfiguration {

    @JsonCreator
    public LookupEntryConfiguration(
        @JsonProperty("uri") String uri,
        @JsonProperty("id") Long id,
        @JsonProperty("self") LookupRef self,
        @JsonProperty("aliases") Set<String> aliases,
        @JsonProperty("directEquivalents") Set<LookupRef> directEquivalents,
        @JsonProperty("explicit") Set<LookupRef> explicit,
        @JsonProperty("equivs") Set<LookupRef> equivs,
        @JsonProperty("created") DateTime created,
        @JsonProperty("updated") DateTime updated
    ){}
    
}
