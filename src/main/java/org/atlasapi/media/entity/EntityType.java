package org.atlasapi.media.entity;

import java.util.Map;

import org.atlasapi.media.common.Described;
import org.atlasapi.media.content.container.Brand;
import org.atlasapi.media.content.container.Container;
import org.atlasapi.media.content.container.Series;
import org.atlasapi.media.content.group.ContentGroup;
import org.atlasapi.media.content.item.Clip;
import org.atlasapi.media.content.item.Episode;
import org.atlasapi.media.content.item.Film;
import org.atlasapi.media.content.item.Item;
import org.atlasapi.media.content.item.Song;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;

public enum EntityType {

	ITEM(Item.class),
	CLIP(Clip.class),
	EPISODE(Episode.class),
	FILM(Film.class),
	SONG(Song.class),
	CONTAINER(Container.class),
    CONTENT_GROUP(ContentGroup.class),
	BRAND(Brand.class),
	SERIES(Series.class);
	
	private static Map<String, EntityType> STRING_LOOKUP = Maps.uniqueIndex(ImmutableList.copyOf(values()), Functions.toStringFunction());
	
	private static Map<Class<? extends Described>, EntityType> CLASS_LOOKUP = Maps.uniqueIndex(ImmutableList.copyOf(values()), new Function<EntityType, Class<? extends Described>>(){
		@Override
		public Class<? extends Described> apply(EntityType input) {
			return input.modelClass;
		}
	});

	private final Class<? extends Described> modelClass;
	
	private EntityType(Class<? extends Described> modelClass) {
		this.modelClass = modelClass;
	}
	
	public String toString() {
		return name().toLowerCase();
	}
	
	public Class<? extends Described> getModelClass() {
		return modelClass;
	}
	
	public static EntityType from(String s) {
		EntityType type = STRING_LOOKUP.get(s);
		if (type == null) {
			throw new IllegalArgumentException("Entity type: " + s + " not recognised");
		}
		return type;
	}
	
	public static EntityType from(Described entity) {
		return from(entity.getClass());
	}

	public static EntityType from(Class<? extends Described> entity) {
		EntityType type = CLASS_LOOKUP.get(entity);
		if (type == null) {
			throw new IllegalArgumentException("Entity type: " + entity + " not recognised");
		}
		return type;
	}
}
