package org.atlasapi.output;

import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.metabroadcast.common.text.MoreStrings;

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
    PUBLISHER;
    
    private static final ImmutableSet<Annotation> defaultAnnotations = ImmutableSet.of(
        DESCRIPTION,
        EXTENDED_DESCRIPTION,
        SUB_ITEMS,
        BROADCASTS,
        LOCATIONS,
        PEOPLE,
        CLIPS
    );
    private static final ImmutableSet<Annotation> ALL = ImmutableSet.copyOf(values());
    
    public static final ImmutableSet<Annotation> all() {
        return ALL;
    }
    
    public static final BiMap<String, Annotation> LOOKUP = HashBiMap.create(Maps.uniqueIndex(all(), Functions.compose(MoreStrings.TO_LOWER, Functions.toStringFunction())));
    
    public static final Set<Annotation> defaultAnnotations() {
        return defaultAnnotations;
    }
    
    public String toKey() {
        return name().toLowerCase();
    }
    
    public static final Function<Annotation, String> TO_KEY = new Function<Annotation, String>() {
        @Override
        public String apply(Annotation input) {
            return input.toKey();
        }
    };
}
