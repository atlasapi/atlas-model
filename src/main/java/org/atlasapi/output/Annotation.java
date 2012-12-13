package org.atlasapi.output;

import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.metabroadcast.common.collect.ImmutableOptionalMap;

public enum Annotation {
    //The order of these entries defines the order of output fields
    ID_SUMMARY,
    ID,
    EXTENDED_ID, 
    DESCRIPTION,
    EXTENDED_DESCRIPTION,
    BRAND_REFERENCE,
    BRAND_SUMMARY,
    SERIES_REFERENCE,
    SERIES_SUMMARY,
    SUB_ITEMS,
    CLIPS,
    PEOPLE,
    TOPICS,
    CONTENT_GROUPS,
    SEGMENT_EVENTS,
    RELATED_LINKS,
    KEY_PHRASES,
    BROADCASTS,   
    LOCATIONS,
    FIRST_BROADCASTS,
    NEXT_BROADCASTS,
    AVAILABLE_LOCATIONS,
    UPCOMING, 
    FILTERING_RESOURCE,
    CHANNEL,
    PRODUCTS,
    RECENTLY_BROADCAST,
    CHANNELS,
    CHANNEL_GROUPS,
    HISTORY,
    PARENT,
    VARIATIONS,
    CHANNEL_SUMMARY,
    AUDIT,
    PUBLISHER,
    IMAGES;
    
    public String toKey() {
        return name().toLowerCase();
    }
    
    private static final Function<Annotation, String> TO_KEY = new Function<Annotation, String>() {
        @Override
        public String apply(Annotation input) {
            return input.toKey();
        }
    };
    
    public static final Function<Annotation, String> toKeyFunction() {
        return TO_KEY;
    }

    private static final ImmutableSet<Annotation> ALL = ImmutableSet.copyOf(values());
    
    public static final ImmutableSet<Annotation> all() {
        return ALL;
    }

    private static final Map<String,Optional<Annotation>> lookup = ImmutableOptionalMap.fromMap(Maps.uniqueIndex(all(), TO_KEY));
    
    public static final Map<String, Optional<Annotation>> lookup() {
        return lookup;
    }
    
    public static final Optional<Annotation> fromKey(String key) {
        return lookup.get(key);
    }
    
    public static final Set<Annotation> defaultAnnotations() {
        return defaultAnnotations;
    }

    private static final ImmutableSet<Annotation> defaultAnnotations = ImmutableSet.of(
        DESCRIPTION,
        EXTENDED_DESCRIPTION,
        SUB_ITEMS,
        BROADCASTS,
        LOCATIONS,
        PEOPLE,
        CLIPS
    );
    
}
