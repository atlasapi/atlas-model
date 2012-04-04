package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.intl.Country;

/**
 */
public interface KeyPhraseConfiguration {
    
    @JsonCreator
    Country make(@JsonProperty("phrase") String phrase, @JsonProperty("weighting") Double weighting);
}
