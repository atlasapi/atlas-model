package org.atlasapi.media.entity.simple;

public class ProductLocation {

    private String uri;
    private String availability;
    private String price;
    private String shippingPrice;

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setShippingPrice(String shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public String getUri() {
        return this.uri;
    }

    public String getAvailability() {
        return this.availability;
    }

    public String getPrice() {
        return this.price;
    }

    public String getShippingPrice() {
        return this.shippingPrice;
    }

}
