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

    public ContentIdentifier() {
    }

    public ContentIdentifier(String uri, String type) {
        this.uri = uri;
        this.type = type;
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

        @Override
        public ItemIdentifier copy() {
            ItemIdentifier id = new ItemIdentifier(uri);
            id.setType(type);
            return id;
        }
    }

    public static class EpisodeIdentifier extends ContentIdentifier {
        
        public EpisodeIdentifier() {
        }

        public EpisodeIdentifier(String uri) {
            super(uri, Episode.class.getSimpleName());
        }

        @Override
        public EpisodeIdentifier copy() {
            EpisodeIdentifier id = new EpisodeIdentifier(uri);
            id.setType(type);
            return id;
        }
    }

    public static class FilmIdentifier extends ContentIdentifier {
        
        public FilmIdentifier() {
        }

        public FilmIdentifier(String uri) {
            super(uri, Film.class.getSimpleName());
        }

        @Override
        public FilmIdentifier copy() {
            FilmIdentifier id = new FilmIdentifier(uri);
            id.setType(type);
            return id;
        }
    }

    public static class BrandIdentifier extends ContentIdentifier {
        
        public BrandIdentifier() {
        }

        public BrandIdentifier(String uri) {
            super(uri, Brand.class.getSimpleName());
        }

        @Override
        public BrandIdentifier copy() {
            BrandIdentifier id = new BrandIdentifier(uri);
            id.setType(type);
            return id;
        }
    }

    public static class SeriesIdentifier extends ContentIdentifier {
        
        public SeriesIdentifier() {
        }

        public SeriesIdentifier(String uri) {
            super(uri, Series.class.getSimpleName());
        }

        @Override
        public SeriesIdentifier copy() {
            SeriesIdentifier id = new SeriesIdentifier(uri);
            id.setType(type);
            return id;
        }
    }

    public static class PersonIdentifier extends ContentIdentifier {
        
        public PersonIdentifier() {
        }

        public PersonIdentifier(String uri) {
            super(uri, org.atlasapi.media.entity.Person.class.getSimpleName());
        }

        @Override
        public PersonIdentifier copy() {
            PersonIdentifier id = new PersonIdentifier(uri);
            id.setType(type);
            return id;
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
