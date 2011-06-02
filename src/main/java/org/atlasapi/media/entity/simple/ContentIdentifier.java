package org.atlasapi.media.entity.simple;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.atlasapi.media.entity.Brand;
import org.atlasapi.media.entity.Described;
import org.atlasapi.media.entity.Episode;
import org.atlasapi.media.entity.Film;
import org.atlasapi.media.entity.Series;

import com.google.common.collect.ImmutableMap;

public abstract class ContentIdentifier {

    protected String uri;
    protected String type;
    protected String id;

    public ContentIdentifier() {
    }

    public ContentIdentifier(String uri, String type) {
        this.uri = uri;
        this.type = type;
    }
    
    public ContentIdentifier(String uri, String type, String id) {
        this.uri = uri;
        this.type = type;
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public abstract ContentIdentifier copy();

    public static class ItemIdentifier extends ContentIdentifier {

        public ItemIdentifier() {
        }

        public ItemIdentifier(String uri) {
            super(uri, org.atlasapi.media.entity.Item.class.getSimpleName());
        }
        
        public ItemIdentifier(String uri, String id) {
            super(uri, org.atlasapi.media.entity.Item.class.getSimpleName());
        }

        @Override
        public ItemIdentifier copy() {
            return new ItemIdentifier(uri, id);
        }
    }

    public static class EpisodeIdentifier extends ContentIdentifier {
        
        public EpisodeIdentifier() {
        }

        public EpisodeIdentifier(String uri) {
            super(uri, Episode.class.getSimpleName());
        }
        
        public EpisodeIdentifier(String uri, String id) {
            super(uri, Episode.class.getSimpleName(), id);
        }

        @Override
        public EpisodeIdentifier copy() {
            return new EpisodeIdentifier(uri, id);
        }
    }

    public static class FilmIdentifier extends ContentIdentifier {
        
        public FilmIdentifier() {
        }

        public FilmIdentifier(String uri) {
            super(uri, Film.class.getSimpleName());
        }
        
        public FilmIdentifier(String uri, String id) {
            super(uri, Film.class.getSimpleName(), id);
        }

        @Override
        public FilmIdentifier copy() {
            return new FilmIdentifier(uri, id);
        }
    }

    public static class BrandIdentifier extends ContentIdentifier {
        
        public BrandIdentifier() {
        }

        public BrandIdentifier(String uri) {
            super(uri, Brand.class.getSimpleName());
        }
        
        public BrandIdentifier(String uri, String id) {
            super(uri, Brand.class.getSimpleName(), id);
        }

        @Override
        public BrandIdentifier copy() {
            return new BrandIdentifier(uri, id);
        }
    }

    public static class SeriesIdentifier extends ContentIdentifier {
        
        public SeriesIdentifier() {
        }

        public SeriesIdentifier(String uri) {
            super(uri, Series.class.getSimpleName());
        }
        
        public SeriesIdentifier(String uri, String id) {
            super(uri, Series.class.getSimpleName(), id);
        }

        @Override
        public SeriesIdentifier copy() {
            return new SeriesIdentifier(uri, id);
        }
    }

    public static class PersonIdentifier extends ContentIdentifier {
        
        public PersonIdentifier() {
        }

        public PersonIdentifier(String uri) {
            super(uri, org.atlasapi.media.entity.Person.class.getSimpleName());
        }
        
        public PersonIdentifier(String uri, String id) {
            super(uri, org.atlasapi.media.entity.Person.class.getSimpleName(), id);
        }

        @Override
        public PersonIdentifier copy() {
            return new PersonIdentifier(uri, id);
        }
    }

    private static Map<String, Class<? extends ContentIdentifier>> typeMap = ImmutableMap.<String, Class<? extends ContentIdentifier>> builder()
            .put(org.atlasapi.media.entity.Person.class.getSimpleName(), PersonIdentifier.class).put(Series.class.getSimpleName(), SeriesIdentifier.class)
            .put(Brand.class.getSimpleName(), BrandIdentifier.class).put(org.atlasapi.media.entity.Item.class.getSimpleName(), ItemIdentifier.class)
            .put(Episode.class.getSimpleName(), EpisodeIdentifier.class).put(Film.class.getSimpleName(), FilmIdentifier.class).build();

    public static ContentIdentifier identifierFor(Described described) {
        try {
            Class<? extends ContentIdentifier> idType = typeMap.get(described.getType());
            Constructor<? extends ContentIdentifier> constructor = idType.getConstructor(String.class);
            ContentIdentifier newInstance = constructor.newInstance(described.getCanonicalUri());
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException("Can't create content identifier for " + described);
        }
    }

    public static ContentIdentifier identifierFrom(String canonicalUri, String type) {
        try {
            Class<? extends ContentIdentifier> idType = typeMap.get(type);
            Constructor<? extends ContentIdentifier> constructor = idType.getConstructor(String.class);
            ContentIdentifier newInstance = constructor.newInstance(canonicalUri);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException("Can't create content identifier for " + canonicalUri);
        }
    }
}
