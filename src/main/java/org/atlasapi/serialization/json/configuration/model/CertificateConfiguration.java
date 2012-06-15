package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.intl.Country;

/**
 */
public abstract class CertificateConfiguration {

    @JsonCreator
    CertificateConfiguration(@JsonProperty("classification") String classification, @JsonProperty("country") Country country) {
    }
}
