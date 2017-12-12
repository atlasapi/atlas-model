package org.atlasapi.output;

import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.metabroadcast.common.text.MoreStrings;

public enum Annotation {

    DESCRIPTION,
    EXTENDED_DESCRIPTION,
    BRAND_SUMMARY,   
    SERIES_SUMMARY,
    SUB_ITEMS,
    CLIPS,
    PEOPLE,
    PEOPLE_DETAIL,
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
    PUBLISHER,
    AUDIT,
    SERIES,
    IMAGES,
    SIMILAR,
    V4_ALIASES, 
    REVIEWS,
    CHANNEL_GROUPS_SUMMARY,
    AUDIENCE_STATISTICS,
    RATINGS,
    EVENTS,
    CONTENT,
    REMOTE_RESPONSES,
    PAYLOAD,
    RESPECT_API_KEY_FOR_EQUIV_LIST,
    ALLOW_MULTIPLE_FROM_SAME_PUBLISHER_IN_EQUIV_LIST,
    ;
    
    private static final ImmutableSet<Annotation> defaultAnnotations = ImmutableSet.of(
        DESCRIPTION,
        EXTENDED_DESCRIPTION,
        SUB_ITEMS,
        BROADCASTS,
        LOCATIONS,
        PEOPLE,
        CLIPS
    );
    public static final BiMap<String, Annotation> LOOKUP = HashBiMap.create(Maps.uniqueIndex(ImmutableList.copyOf(Annotation.values()), Functions.compose(MoreStrings.TO_LOWER, Functions.toStringFunction())));
    
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
