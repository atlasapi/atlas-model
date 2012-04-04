package org.atlasapi.serialization.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.content.item.Broadcast;
import org.atlasapi.media.product.ProductLocation;

/**
 */
@JsonDeserialize(builder=ProductLocation.Builder.class)
public interface ProductLocationConfiguration {
    
    @JsonCreator
    ProductLocation make(@JsonProperty("uri") String uri);
}
