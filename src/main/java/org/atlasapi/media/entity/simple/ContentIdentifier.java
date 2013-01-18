package org.atlasapi.media.entity.simple;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.EntityType;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.metabroadcast.common.ids.NumberToShortStringCodec;

public abstract class ContentIdentifier {

    protected String type;
    protected String id;

    public ContentIdentifier() {
    }

    public ContentIdentifier(String id, String type) {
        this.id = id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
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
        
        public ItemIdentifier(String id) {
            super(id, EntityType.ITEM.toString());
        }

        @Override
        public ItemIdentifier copy() {
            return new ItemIdentifier(id);
        }
    }

    public static class EpisodeIdentifier extends ContentIdentifier {
        
        public EpisodeIdentifier() {
        }

        public EpisodeIdentifier(String id) {
            super(id, EntityType.EPISODE.toString());
        }

        @Override
        public EpisodeIdentifier copy() {
            return new EpisodeIdentifier(id);
        }
    }

    public static class FilmIdentifier extends ContentIdentifier {
        
        public FilmIdentifier() {
        }

        public FilmIdentifier(String id) {
            super(id, EntityType.FILM.toString());
        }

        @Override
        public FilmIdentifier copy() {
            return new FilmIdentifier(id);
        }
    }

    public static class BrandIdentifier extends ContentIdentifier {
        
        public BrandIdentifier() {
        }
        
        public BrandIdentifier(String id) {
            super(id, EntityType.BRAND.toString());
        }

        @Override
        public BrandIdentifier copy() {
            return new BrandIdentifier(id);
        }
    }

    public static class SeriesIdentifier extends ContentIdentifier {
        
        public SeriesIdentifier() {
        }

        public SeriesIdentifier(String id) {
            super(id, EntityType.SERIES.toString());
        }

        @Override
        public SeriesIdentifier copy() {
            return new SeriesIdentifier(id);
        }
    }

    public static class PersonIdentifier extends ContentIdentifier {
        
        public PersonIdentifier() {
        }

        public PersonIdentifier(String id) {
            super(id, EntityType.PERSON.toString());
        }

        @Override
        public PersonIdentifier copy() {
            return new PersonIdentifier(id);
        }
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("type", type)
            .toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ContentIdentifier) {
            ContentIdentifier other = (ContentIdentifier)that;
            return Objects.equal(this.id, other.id) 
                && Objects.equal(this.type, other.type);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    private static Map<EntityType, Class<? extends ContentIdentifier>> typeMap = ImmutableMap.<EntityType, Class<? extends ContentIdentifier>> builder()
            .put(EntityType.PERSON, PersonIdentifier.class)
            .put(EntityType.SERIES, SeriesIdentifier.class)
            .put(EntityType.BRAND, BrandIdentifier.class)
            .put(EntityType.ITEM, ItemIdentifier.class)
            .put(EntityType.EPISODE, EpisodeIdentifier.class)
            .put(EntityType.FILM, FilmIdentifier.class)
    .build();

	private static ContentIdentifier create(EntityType type, String id) {
		try {
            Class<? extends ContentIdentifier> idType = typeMap.get(type);
            Constructor<? extends ContentIdentifier> constructor = idType.getConstructor(String.class);
            return constructor.newInstance(id);
        } catch (Exception e) {
            throw new RuntimeException("Can't create content identifier for " + id);
        }
	}
    
    public static ContentIdentifier identifierFor(ChildRef childRef, NumberToShortStringCodec idCodec) {
        String id = childRef.getId() != null ? idCodec.encode(childRef.getId().toBigInteger())
                                             : null;
        return create(childRef.getType(), id);
    }

    public static ContentIdentifier identifierFrom(String canonicalUri, String type) {
        try {
            Class<? extends ContentIdentifier> idType = typeMap.get(EntityType.from(type));
            Constructor<? extends ContentIdentifier> constructor = idType.getConstructor(String.class);
            ContentIdentifier newInstance = constructor.newInstance(canonicalUri);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException("Can't create content identifier for " + canonicalUri);
        }
    }
    
    public static final Function<ContentIdentifier, String> TO_ID = new Function<ContentIdentifier, String>() {
        @Override
        public String apply(ContentIdentifier input) {
            return input.getId();
        }
    };
    
    public static final Function<ContentIdentifier, ContentIdentifier> COPY = new Function<ContentIdentifier, ContentIdentifier>() {
        @Override
        public ContentIdentifier apply(ContentIdentifier input) {
            return input.copy();
        }
    };
}
