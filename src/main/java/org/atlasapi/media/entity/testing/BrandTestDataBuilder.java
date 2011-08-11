package org.atlasapi.media.entity.testing;

import java.util.Set;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;

public class BrandTestDataBuilder {
    
    private String uri;
    private String curie;
    private Set<ChildRef> childRefs;
    private Publisher publisher;
    private String title;
    
    private BrandTestDataBuilder() {
    }
    
    public static BrandTestDataBuilder brand() {
        return new BrandTestDataBuilder();
    }
    
    public BrandTestDataBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    
    public BrandTestDataBuilder withChildRefs(Iterable<ChildRef> childRefs) {
        this.childRefs = ImmutableSet.copyOf(childRefs);
        return this;
    }
    
    public Brand build() {
        Brand brand = new Brand(uri, curie, publisher);
        
        brand.setTitle(title);
        brand.setChildRefs(childRefs);
        
        return brand;
    }
}
