package org.atlasapi.serialization.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.atlasapi.media.product.Product;

/**
 */
@JsonDeserialize(builder=Product.Builder.class)
public interface ProductConfiguration {
    
}
