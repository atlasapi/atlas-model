package org.atlasapi.media.product;

import org.atlasapi.media.content.Publisher;

import com.google.common.base.Optional;

public interface ProductStore {

    Optional<Product> productForSourceIdentified(Publisher source, String sourceIdentifier);
    
    Product store(Product product);
    
}
