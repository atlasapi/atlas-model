package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.intl.Country;
import org.atlasapi.media.content.item.Certificate;

/**
 */
public interface CertificateConfiguration {
    
    @JsonCreator
    Certificate make(@JsonProperty("classification") String classification, @JsonProperty("country") Country country);
}
