package org.atlasapi.serialization.json.configuration.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.metabroadcast.common.currency.Price;

/**
 */
public abstract class  ProductLocationConfiguration {
    
    @JsonCreator
    ProductLocationConfiguration(@JsonProperty("uri") String uri, @JsonProperty("price") Price price, @JsonProperty("shippingPrice") Price shippingPrice, @JsonProperty("availability") String availability) {
    }
}
