package org.atlasapi.media.entity.testing;

import java.util.Set;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Publisher;

import com.google.common.collect.ImmutableSet;

public class BrandTestDataBuilder {
    
    private String uri;
    private String curie;
    private Set<ChildRef> childRefs = ImmutableSet.of();
    private Publisher publisher;
    private String title;
    private Long id;
    private Iterable<String> genres;
    
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
    
    public BrandTestDataBuilder withCanonicalUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public BrandTestDataBuilder withGenres(Iterable<String> genres) {
        this.genres = genres;
        return this;
    }
    
    public BrandTestDataBuilder withId(long id) {
        this.id = id;
        return this;
    }
    
    public Brand build() {
        Brand brand = new Brand(uri, curie, publisher);
        
        brand.setTitle(title);
        brand.setChildRefs(childRefs);
        brand.setId(id);
        
        if (genres != null) {
            brand.setGenres(genres);
        }
        
        return brand;
    }
}
