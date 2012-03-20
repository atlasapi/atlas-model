package org.atlasapi.media.product;

import com.google.common.base.Optional;

public interface ProductResolver {

    Optional<Product> productForId(long id);
    
    Iterable<Product> products();
    
    Iterable<Product> productsForContent(String canonicalUri);
    
}
