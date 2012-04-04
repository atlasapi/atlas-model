package org.atlasapi.media.product;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.common.Description;
import org.atlasapi.media.common.Identified;
import org.atlasapi.media.common.Identifier;

import com.google.common.collect.ImmutableSet;

public final class Product extends Identified implements Described {

    public static final Builder builder() {
        return new Builder();
    }

    public static final class Builder extends Identified.Builder<Product, Builder> {

        private String gtin;
        private ProductType type;
        private Description description;
        private Integer year;
        private ImmutableSet<String> content;
        private ImmutableSet<ProductLocation> locations;

        @Override
        public Builder copy(Product product) {
            super.copy(product);
            this.gtin = product.gtin;
            this.type = product.type;
            this.year = product.year;
            this.description = product.description;
            this.content = product.content;
            this.locations = product.locations;
            return builder();
        }

        public Builder withGtin(String gtin) {
            this.gtin = gtin;
            return builder();
        }

        public Builder withType(ProductType type) {
            this.type = type;
            return builder();
        }

        public Builder withYear(Integer year) {
            this.year = year;
            return builder();
        }

        public Builder withContent(Iterable<String> content) {
            this.content = ImmutableSet.copyOf(content);
            return builder();
        }

        public Builder withLocations(Iterable<ProductLocation> locations) {
            this.locations = ImmutableSet.copyOf(locations);
            return builder();
        }

        @Override
        protected Builder builder() {
            return this;
        }

        @Override
        public Product build() {
            return new Product(this);
        }

    }

    private final String gtin;
    private final ProductType type;
    private final Description description;
    private final Integer year;
    private final ImmutableSet<String> content;
    private final ImmutableSet<ProductLocation> locations;

    public Product(Builder builder) {
        super(builder);
        this.gtin = builder.gtin;
        this.type = builder.type;
        this.description = builder.description;
        this.year = builder.year;
        this.content = builder.content;
        this.locations = builder.locations;
    }

    @Override
    public Builder copy() {
        return new Builder().copy(this);
    }

    public String gtin() {
        return this.gtin;
    }

    public ProductType productType() {
        return this.type;
    }
    
    public Description description() {
        return this.description;
    }

    public Integer year() {
        return this.year;
    }

    public ImmutableSet<String> content() {
        return this.content;
    }

    public ImmutableSet<ProductLocation> locations() {
        return this.locations;
    }
    
    @Override
    public ProductIdentifier toIdentifier() {
        return new ProductIdentifier(this);
    }
    
    private static final class ProductIdentifier extends Identifier {

        public ProductIdentifier(Product product) {
            super(product);
        }
        
    }
}
