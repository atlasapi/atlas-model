package org.atlasapi.media.product;

import com.metabroadcast.common.currency.Price;

public class ProductLocation {

    public static final Builder builder(String uri) {
        return new Builder(uri);
    }
    
    public static class Builder {

        private final String uri;
        private Price price;
        private Price shippingPrice;
        private String availability;

        public Builder(String uri) {
            this.uri = uri;
        }

        public Builder withPrice(Price price) {
            this.price = price;
            return this;
        }

        public Builder withShippingPrice(Price price) {
            this.shippingPrice = price;
            return this;
        }

        public Builder withAvailability(String availability) {
            this.availability = availability;
            return this;
        }

        public ProductLocation build() {
            return new ProductLocation(uri, price, shippingPrice, availability);
        }

    }

    private final String uri;
    private final Price price;
    private final Price shippingPrice;
    private final String availability;

    private ProductLocation(String uri, Price price, Price shippingPrice, String availability) {
        this.uri = uri;
        this.price = price;
        this.shippingPrice = shippingPrice;
        this.availability = availability;
    }

    public Price getPrice() {
        return price;
    }

    public String getUri() {
        return uri;
    }

    public Price getShippingPrice() {
        return shippingPrice;
    }

    public String getAvailability() {
        return availability;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof ProductLocation) {
            ProductLocation other = (ProductLocation) that;
            return getUri().equals(other.getUri());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getUri().hashCode();
    }

    @Override
    public String toString() {
        return String.format("Product location: %s", getUri());
    }

}
