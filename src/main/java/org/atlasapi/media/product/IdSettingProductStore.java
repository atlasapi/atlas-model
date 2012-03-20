package org.atlasapi.media.product;

import org.atlasapi.media.entity.Publisher;

import com.google.common.base.Optional;
import com.metabroadcast.common.ids.IdGenerator;

public class IdSettingProductStore implements ProductStore {

    private final IdGenerator idGenerator;
    private final ProductStore delegate;

    public IdSettingProductStore(ProductStore delegate, IdGenerator idGenerator) {
        this.delegate = delegate;
        this.idGenerator = idGenerator;
    }
    
    @Override
    public Optional<Product> productForSourceIdentified(Publisher source, String sourceIdentifier) {
        return delegate.productForSourceIdentified(source, sourceIdentifier);
    }

    @Override
    public Product store(Product product) {
        Optional<Product> existingProduct = delegate.productForSourceIdentified(product.getPublisher(), product.getCanonicalUri());
        if (existingProduct.isPresent()) {
            product.setId(existingProduct.get().getId());
        } else {
            product.setId(idGenerator.generateRaw());
        }
        return delegate.store(product);
    }

}
