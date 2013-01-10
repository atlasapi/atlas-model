package org.atlasapi.media.entity.testing;

import java.util.List;
import java.util.Set;

import org.atlasapi.media.entity.Alias;
import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.Clip;
import org.atlasapi.media.entity.Container;
import org.atlasapi.media.entity.Item;
import org.atlasapi.media.entity.MediaType;
import org.atlasapi.media.entity.Publisher;
import org.atlasapi.media.entity.Version;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class ComplexItemTestDataBuilder {
    private static Long uniqueId = 1L;
    
    private String uri;
    private String curie;
    
    private Set<String> aliasUrls;
    private Set<Alias> aliases;
    private Container brand;
    private List<Clip> clips;

    private MediaType mediaType;

    private String description;
    private Set<String> genres;
    private String image;
    private Publisher publisher;
    private Set<String> tags;
    private String thumbnail;
    private String title;
    private Long id;
    private Set<Version> versions;
    
    public static ComplexItemTestDataBuilder complexItem() {
        return new ComplexItemTestDataBuilder();
    }
    
    private ComplexItemTestDataBuilder() {
        uri = "http://test.metabroadcast.com/item/default";
        curie = "mbtest:i-default";
        
        id = uniqueId++;
        uri = "http://test.metabroadcast.com/unique/items/" + id;
        curie = "mbtest:i-" + id;
        
        aliasUrls = ImmutableSet.of();
        aliases = ImmutableSet.of();
        brand = null;
        clips = ImmutableList.of();
        mediaType = null;
        description = "Default test item created by ItemTestDataBuilder";
        genres = ImmutableSet.of("http://test.metabroadcast.com/genres/default");
        image = "http://test.metabroadcast.com/images/default";
        publisher = Publisher.BBC;
        tags = ImmutableSet.of();
        thumbnail = "http://test.metabroadcast.com/thumbnails/default";
        title = "Default Test Item";
        versions = ImmutableSet.of();
    }
    
    public Item build() {
        return buildItemAttributes(new Item());
    }
    
    private Item buildItemAttributes(Item item) {
        item.setAliasUrls(aliasUrls);
        item.setAliases(aliases);
        if (brand != null) {
            item.setContainer(brand);
        }
        item.setId(id);
        item.setVersions(versions);
        item.setClips(clips);
        item.setMediaType(mediaType);
        item.setMediaType(mediaType);
        item.setDescription(description);
        item.setGenres(genres);
        item.setImage(image);
        item.setPublisher(publisher);
        item.setTags(tags);
        item.setThumbnail(thumbnail);
        item.setTitle(title);
        item.setCanonicalUri(uri);
        item.setCurie(curie);
        return item;
    }
    
    public ComplexItemTestDataBuilder withClips(Clip... clips) {
        this.clips = ImmutableList.copyOf(clips);
        return this;
    }
    
    public ComplexItemTestDataBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    
    public ComplexItemTestDataBuilder withUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public ComplexItemTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }
    
    public ComplexItemTestDataBuilder withTags(String... tags) {
        this.tags = ImmutableSet.copyOf(tags);
        return this;
    }
    
    public ComplexItemTestDataBuilder withAliasUrls(String... aliases) {
        this.aliasUrls = ImmutableSet.copyOf(aliases);
        return this;
    }
    
    public ComplexItemTestDataBuilder withAliases(Alias... aliases) {
        this.aliases = ImmutableSet.copyOf(aliases);
        return this;
    }
    
    public ComplexItemTestDataBuilder withBrand(Container brand) {
        this.brand = brand;
        
        brand.setChildRefs(ImmutableSet.<ChildRef>builder().addAll(brand.getChildRefs()).add(this.build().childRef()).build());
        
        return this;
    }
    
    public ComplexItemTestDataBuilder withVersions(Version...versions) {
        this.versions = ImmutableSet.copyOf(versions);
        return this;
    }
    
    public ComplexItemTestDataBuilder withDescription(String desciption) {
        this.description = desciption;
        return this;
    }
}
