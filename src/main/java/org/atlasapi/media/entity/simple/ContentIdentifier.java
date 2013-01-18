package org.atlasapi.media.entity.simple;

import java.math.BigInteger;

import org.atlasapi.media.entity.ChildRef;
import org.atlasapi.media.entity.EntityType;
import org.atlasapi.media.entity.SeriesRef;

import com.google.common.base.Function;
import com.google.common.base.Objects;
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
        
        private Integer seriesNumber;

        public SeriesIdentifier() {
            
        }
        
        public SeriesIdentifier(Integer seriesNumber, String id) {
            super(EntityType.SERIES.toString(), id);
            this.seriesNumber = seriesNumber;
        }

        @Override
        public SeriesIdentifier copy() {
            return new SeriesIdentifier(seriesNumber, id);
        }
        
        public Integer getSeriesNumber() {
            return seriesNumber;
        }
        
        public void setSeriesNumber(Integer seriesNumber) {
            this.seriesNumber = seriesNumber;
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

	private static ContentIdentifier create(EntityType type, String id, Integer seriesNumber) {
	    switch (type) {
        case BRAND:
            return new BrandIdentifier(id);
        case EPISODE:
            return new EpisodeIdentifier(id);
        case FILM:
            return new FilmIdentifier(id);
        case ITEM:
            return new ItemIdentifier(id);
        case PERSON:
            return new PersonIdentifier(id);
        case SERIES:
            return new SeriesIdentifier(seriesNumber, id);
        default:
            throw new RuntimeException("Can't create content identifier for " + id);	    
	    }
	}
    
    public static ContentIdentifier identifierFor(ChildRef childRef, NumberToShortStringCodec idCodec) {
        return create(childRef.getType(), idFrom(idCodec, childRef), null);
    }
    
    public static SeriesIdentifier seriesIdentifierFor(SeriesRef seriesRef, NumberToShortStringCodec idCodec) {
        return (SeriesIdentifier) create(EntityType.SERIES, idFrom(idCodec, seriesRef), 
                seriesRef.getSeriesNumber());
    }

    private static String idFrom(NumberToShortStringCodec idCodec, ChildRef childRef) {
        return childRef.getId() != null ? idCodec.encode(childRef.getId().toBigInteger())
                                             : null;
    }
    
    private static String idFrom(NumberToShortStringCodec idCodec, SeriesRef seriesRef) {
        return seriesRef.getId() != null ? idCodec.encode(seriesRef.getId().toBigInteger())
                                             : null;
    }
    
    public static ContentIdentifier identifierFrom(String id, String canonicalUri, String type) {
        EntityType from = EntityType.from(type);
        if (EntityType.SERIES.equals(from)) {
            throw new IllegalArgumentException("Series not supported, use seriesIdentiferFrom instead");
        }
        
        return create(from, id, null);
    }
    
    public static ContentIdentifier seriesIdentifierFrom(String canonicalUri, String id, Integer seriesNumber) {
        return create(EntityType.SERIES, id, seriesNumber);
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
