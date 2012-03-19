package org.atlasapi.media.entity.simple;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.atlasapi.media.vocabulary.PLAY_SIMPLE_XML;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;

@XmlRootElement(namespace=PLAY_SIMPLE_XML.NS, name="products")
@XmlType(name="products", namespace=PLAY_SIMPLE_XML.NS)
public class ProductQueryResult {

    private List<Product> products = Lists.newArrayList();

    public void add(Product content) {
        products.add(content);
    }

    @XmlElements({ 
        @XmlElement(name = "product", type = Product.class, namespace=PLAY_SIMPLE_XML.NS)
    })
    public List<Product> getContents() {
        return products;
    }
    
    public void setContents(Iterable<Product> items) {
        this.products = Lists.newArrayList(items);
    }
    
    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public int hashCode() {
        return products.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this instanceof ProductQueryResult) {
            ProductQueryResult other = (ProductQueryResult) obj;
            return products.equals(other.products);
        }
        return false;
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).addValue(products).toString();
    }
}