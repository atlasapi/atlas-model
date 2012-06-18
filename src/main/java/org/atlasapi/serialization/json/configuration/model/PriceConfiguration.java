package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Currency;

/**
 */
public abstract class PriceConfiguration {

    @JsonCreator
    PriceConfiguration(@JsonProperty("currency") Currency currency, @JsonProperty("amount") int amount) {
    }
}
