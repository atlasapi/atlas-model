package org.atlasapi.media.entity.testing;

import java.util.List;
import java.util.Set;

import org.atlasapi.media.entity.simple.Item;
import org.atlasapi.media.entity.simple.Playlist;
import org.atlasapi.media.entity.simple.PublisherDetails;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

public class PlaylistTestDataBuilder {
    private static Integer uniqueId = 1;
    
    private String uri;
    private String curie;
    
    private Set<String> aliases;
    private List<Item> clips;
    private Set<String> containedIn;
    private String contentType;
    private String description;
    private Set<String> genres;
    private String image;
    private PublisherDetails publisher;
    private Set<String> sameAs;
    private Set<String> tags;
    private String thumbnail;
    private String title;
    
    private List<Item> items;
    private List<Playlist> playlists;
    
    public static PlaylistTestDataBuilder playlist() {
        return new PlaylistTestDataBuilder();
    }
    
    private PlaylistTestDataBuilder() {
        resetAttributes();
    }
    
    private void resetAttributes() {
        
        uri = "http://test.metabroadcast.com/item/default";
        curie = "mbtest:i-default";
        
        int id = uniqueId++;
        uri = "http://test.metabroadcast.com/unique/items/" + id;
        curie = "mbtest:i-" + id;
        
        aliases = ImmutableSet.of();
        clips = ImmutableList.of();
        containedIn = ImmutableSet.of();
        contentType = null;
        description = "Default test item created by PlaylistTestDataBuilder";
        genres = ImmutableSet.of("http://test.metabroadcast.com/genres/default");
        image = "http://test.metabroadcast.com/images/default";
        publisher = defaultPublisher();
        sameAs = ImmutableSet.of();
        tags = ImmutableSet.of();
        thumbnail = "http://test.metabroadcast.com/thumbnails/default";
        title = "Default Test Item";
        items = ImmutableList.of();
        playlists = ImmutableList.of();
    }
    
    private PublisherDetails defaultPublisher() {
        PublisherDetails publisher = new PublisherDetails("http://test.metabroadcast.com/publishers/default");
        publisher.setName("Metabroadcast Test");
        publisher.setCountry("UK");
        return publisher;
    }
    
    public Playlist build() {
        return buildPlaylistAttributes(new Playlist());
    }
    
    private Playlist buildPlaylistAttributes(Playlist playlist) {
        playlist.setAliases(aliases);
        playlist.setClips(clips);
        playlist.setContainedIn(containedIn);
        playlist.setContentType(contentType);
        playlist.setDescription(description);
        playlist.setGenres(genres);
        playlist.setImage(image);
        playlist.setPublisher(publisher);
        playlist.setSameAs(sameAs);
        playlist.setTags(tags);
        playlist.setThumbnail(thumbnail);
        playlist.setTitle(title);
        playlist.setUri(uri);
        playlist.setCurie(curie);
        playlist.setItems(items);
        playlist.setPlaylists(playlists);
        return playlist;
    }
    
    public PlaylistTestDataBuilder withSameAs(String... sameAs) {
        this.sameAs = ImmutableSortedSet.copyOf(sameAs);
        return this;
    }
    
    public PlaylistTestDataBuilder withTitle(String title) {
        this.title = title;
        return this;
    }
    
    public PlaylistTestDataBuilder withUri(String uri) {
        this.uri = uri;
        return this;
    }
    
    public PlaylistTestDataBuilder withItems(Item... items) {
        this.items = ImmutableList.copyOf(items);
        return this;
    }
}
